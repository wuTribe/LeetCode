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

        while (true) {
            boss.select();
            Iterator<SelectionKey> iterator = boss.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                }
            }
        }
    }


    @Slf4j
    static class Work implements Runnable {

        private Thread thread;
        private Selector worker;
        private String name;

        private volatile boolean start;

        public Work(String name) {
            this.name = name;
        }

        // 初始化线程和 selector
        public void register() throws IOException {
            if (!start) {
                this.thread = new Thread(this, name);
                this.thread.start();
                this.worker = Selector.open();
                this.start = true;
            }
        }


        @Override
        public void run() {
             while (true) {
                 try {
                     this.worker.select();
                     Iterator<SelectionKey> iterator = worker.selectedKeys().iterator();
                     while (iterator.hasNext()) {
                         SelectionKey key = iterator.next();
                         iterator.remove();
                         if (key.isReadable()) {
                             ByteBuffer buffer = ByteBuffer.allocate(16);
                             SocketChannel channel = (SocketChannel) key.channel();
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
