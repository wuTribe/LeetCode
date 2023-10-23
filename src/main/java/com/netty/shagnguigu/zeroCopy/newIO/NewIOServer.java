package com.netty.shagnguigu.zeroCopy.newIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NewIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(7001));

        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
        while (true) {
            System.out.println("等待连接。。。");
            SocketChannel socketChannel = serverSocketChannel.accept();
            System.out.println("连接成功。。。");
            try {
                while (socketChannel.read(byteBuffer) != -1) {
                    // 重读
                    byteBuffer.rewind();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("处理完成。。。");
        }
    }
}
