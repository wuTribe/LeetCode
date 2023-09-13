package com.heima.c4;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

import static com.heima.c1.ByteBufferUtil.debugAll;

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
        // 1. 创建固定数量 worker 并初始化
        Worker worker = new Worker("worker-0");
        worker.register();

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
                    sc.register(worker.selector, SelectionKey.OP_READ, null);
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

        public Worker(String name) {
            this.name = name;
        }

        // 初始化线程和 selector
        public void register() throws IOException {
            if (!start) {
                this.thread = new Thread(this, name);
                this.selector = Selector.open();
                this.start = true;
                this.thread.start();
            }
        }


        @Override
        public void run() {
             while (true) {
                 try {
                     this.selector.select();
                     Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                     while (iterator.hasNext()) {
                         SelectionKey key = iterator.next();
                         iterator.remove();
                         if (key.isReadable()) {
                             ByteBuffer buffer = ByteBuffer.allocate(16);
                             SocketChannel channel = (SocketChannel) key.channel();
                             log.debug("read ...{}", channel.getRemoteAddress());
                             channel.read(buffer);
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
