package com.wu.groupChat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GroupChatClient {
    private final String HOST = "127.0.0.1";
    private final int PORT = 6667;
    private final Selector selector;
    private final SocketChannel socketChannel;
    private final String username;

    public GroupChatClient() {
        try {
            this.selector = Selector.open();
            // 连接服务器 里面实际上调用的是 socketChannel.connect 方法
            this.socketChannel = SocketChannel.open(new InetSocketAddress(HOST, PORT));
            this.socketChannel.configureBlocking(false);
            this.socketChannel.register(this.selector, SelectionKey.OP_READ);
            // 设置用户名
            this.username = socketChannel.getLocalAddress().toString().substring(1);
            System.out.println(this.username + " is ok! ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 向服务器发送消息
    public void sendInfo(String info) {
        info = this.username + " 说：" + info;
        try {
            this.socketChannel.write(ByteBuffer.wrap(info.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readInfo() {
        try {
            int readChannels = this.selector.select();
            if (readChannels <= 0) {
                System.out.println("没有其他的工作，可以做其他事情");
            }

            Iterator<SelectionKey> keyIterator = this.selector.selectedKeys().iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    channel.read(byteBuffer);
                    String msg = new String(byteBuffer.array());
                    System.out.println("msg = " + msg.trim());
                }
                keyIterator.remove();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        GroupChatClient groupChatClient = new GroupChatClient();
        // 从服务器拉数据
        new Thread(() -> {
            while (true) {
                groupChatClient.readInfo();
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        // 发送到服务端
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            groupChatClient.sendInfo(scanner.nextLine());
        }
    }
}
