package com.heima.c4;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import static com.heima.c1.ByteBufferUtil.debugAll;

/**
 * boss 线程与 worker 线程：
 * ｜ ------- boss 线程调用 register 方法。worker 调用 worker.selector.select 方法，后者会导致前者阻塞，
 * ｜ ------- 通过队列的解偶，将 channel 传入 worker 中注册
 */
@Slf4j
public class MultiThreadServer {
    public static void main(String[] args) throws IOException {
        Thread.currentThread().setName("boss");
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        Selector boss = Selector.open();
        SelectionKey bossKey = ssc.register(boss, 0, null);
        bossKey.interestOps(SelectionKey.OP_ACCEPT);
        ssc.bind(new InetSocketAddress(8888));
        // 创建多个 worker，负载均衡调用（轮训）
        // 当前机器可用的核心数，但是如果这里使用 docker 部署，在 JDK10 以下，得到的都是物理机的CPU核心数
        Worker[] workers = new Worker[Runtime.getRuntime().availableProcessors()];
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new Worker("worker-" + i);
        }

        int wIndex = 0;
        while (true) {
            boss.select();
            Iterator<SelectionKey> iterator = boss.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    log.debug("connected...{}", sc.getRemoteAddress());
                    // 2. 关联 selector
                    log.debug("before register...{}", sc.getRemoteAddress());
                    // 原先先启动 selector 线程，由于没有注册 channel，select 方法阻塞，影响 boss 线程 register，导致失败
                    // 解决办法1：先注册再 select
                    workers[wIndex++ % workers.length].register(sc);
                    log.debug("after register...{}", sc.getRemoteAddress());
                }
            }
        }
    }


    @Slf4j
    static class Worker implements Runnable {

        private Thread thread;
        private Selector selector;
        private String name;

        private volatile boolean start;

        private ConcurrentLinkedQueue<Runnable> queue;

        public Worker(String name) {
            this.name = name;
        }

        // 初始化线程和 selector
        public void register(SocketChannel sc) throws IOException {
            if (!start) {
                this.thread = new Thread(this, name);
                this.selector = Selector.open();
                this.start = true;
                this.queue = new ConcurrentLinkedQueue<>();
                this.thread.start();
            }
            // 向队列中添加了任务，并没有立即执行
            this.queue.add(() -> {
                 try {
                    sc.register(this.selector, SelectionKey.OP_READ);
                } catch (ClosedChannelException e) {
                    log.error("", e);
                }
            });
            this.selector.wakeup(); // 主动唤醒 selector.select() 不阻塞
        }


        @Override
        public void run() {
             while (true) {
                 try {
                     this.selector.select();
                     Runnable task = queue.poll();
                     if (task != null) {
                         task.run();
                     }
                     Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                     while (iterator.hasNext()) {
                         SelectionKey key = iterator.next();
                         iterator.remove();
                         if (key.isReadable()) {
                             ByteBuffer buffer = ByteBuffer.allocate(16);
                             SocketChannel channel = (SocketChannel) key.channel();
                             log.debug("read ...{}", channel.getRemoteAddress());
                             if (channel.read(buffer) == -1) { // 如果是正常断开，返回值为 -1
                                 key.cancel();
                                 continue;
                             }
                             buffer.flip();
                             debugAll(buffer);
                         }
                     }
                 } catch (IOException e) {
                     log.error("", e);
                 }
             }
        }
    }
}
