package com.shagnguigu.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * Created by wuyufan on 2022/10/31.
 */
public class ScatteringAndGatheringTest {
    // 聚合了多个选择器，可以同事并发处理多个客户端连接
    // 有一个事件循环的线程，线程通常将非阻塞IO的空闲事件用于在其他通道上进行IO操作
    // 读写操作非阻塞


    // 一个 buffer 不够用可以用两个
    @Test
    public void scatteringAndGathering() throws IOException {
        // 使用 ServerSocketChannel 和 SocketChannel 网络
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);
        // 绑定端口并启动
        serverSocketChannel.socket().bind(inetSocketAddress);

        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        System.out.println("服务器等待连接中。。。。");

        SocketChannel socketChannel = serverSocketChannel.accept();
        int messageLength = 8;
        while (true) {
            int byteRead = 0;
            while (byteRead < messageLength) {
                long read = socketChannel.read(byteBuffers);
                byteRead += read; // 累计读取的字节数
                System.out.println("byteRead = " + byteRead);
                Arrays.stream(byteBuffers).map(ByteBuffer::toString).forEach(System.out::println);
            }
            // 翻转模式
            Arrays.stream(byteBuffers).forEach(Buffer::flip);
            // 数据独处显示到客户端
            int byteWrite = 0;
            while (byteWrite < messageLength) {
                long write = socketChannel.write(byteBuffers);
                byteWrite += write;
            }
            // 复位
            Arrays.stream(byteBuffers).forEach(ByteBuffer::clear);
            System.out.println("------------------------------");
            System.out.println("byteRead = " + byteRead);
            System.out.println("byteWrite = " + byteWrite);
            System.out.println("messageLength = " + messageLength);
            System.out.println("===============================");
        }
    }
}
