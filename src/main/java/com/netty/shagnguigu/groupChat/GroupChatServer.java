package com.netty.shagnguigu.groupChat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class GroupChatServer {
    private final Selector selector;
    private final ServerSocketChannel listenChannel;
    private static final int PORT = 6667;

    public GroupChatServer() {
        try {
            this.selector = Selector.open();
            this.listenChannel = ServerSocketChannel.open();

            this.listenChannel.bind(new InetSocketAddress(PORT));
            this.listenChannel.configureBlocking(false);
            this.listenChannel.register(this.selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void listen() {
        while (true) {
            try {
                int count = this.selector.select();
                if (count <= 0) {
                    System.out.println("监听等待....");
                }

                Iterator<SelectionKey> keyIterator = this.selector.selectedKeys().iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    if (key.isAcceptable()) {
                        SocketChannel socketChannel = this.listenChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(this.selector, SelectionKey.OP_READ);
                        System.out.println(socketChannel.getRemoteAddress() + " 上线.... ");
                    }
                    if (key.isReadable()) {
                        readData(key);
                    }
                    keyIterator.remove();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void readData(SelectionKey key) {
        SocketChannel channel = (SocketChannel) key.channel();
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = channel.read(buffer);
            if (count > 0) {
                String msg = new String(buffer.array());
                // 输出消息
                System.out.println("from 客户端： " + msg);
                // 向其他客户端转发消息，排除自身
                sendInfoToOtherClients(msg, channel);
            }
        } catch (IOException e) {
            try {
                System.out.println(channel.getRemoteAddress() + " 离线了... ");
                // 取消注册
                key.cancel();
                // 关闭通道
                channel.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void sendInfoToOtherClients(String msg, SocketChannel self) throws IOException {
        System.out.println("服务器转发消息中....");
        System.out.println("服务器转发数据给客户端线程: " + Thread.currentThread().getName());
        // 遍历，所有注册到 select 上的 Socket Channel，并排除 self
        for (SelectionKey key : this.selector.keys()) {
            Channel targetChannel = key.channel();
            if ((targetChannel instanceof SocketChannel) && (targetChannel != self)) {
                SocketChannel dest = (SocketChannel) targetChannel;
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                dest.write(buffer);
            }
        }
    }


    public static void main(String[] args) {
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();
    }
}
