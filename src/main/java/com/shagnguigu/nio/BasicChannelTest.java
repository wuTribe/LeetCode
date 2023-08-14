package com.shagnguigu.nio;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

import static com.shagnguigu.Constant.FILE;

/**
 * Created by wuyufan on 2022/10/19.
 */
public class BasicChannelTest {
    // FileChannel：用于文件数据读写
    // -- 读写通道
    // -- 通道之间的拷贝（底层使用的是零拷贝）

    // DatagramChannel：用于 UDP 数据读写

    // 下面两个用于 TCP 数据读写
    // ServerSocketChannel -- ServerSocketChannelImpl（类似 ServerSocketChannel）
    // SocketChannel -- SocketChannelImpl （类似 Socket）

    // 写出 channel
    @Test
    public void writeChannelTest() throws IOException {
        String str = "hello,哈喽";
        FileOutputStream fileOutputStream = new FileOutputStream(FILE);
        FileChannel channel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(str.getBytes(StandardCharsets.UTF_8));
        byteBuffer.flip();
        // 写出到文件
        channel.write(byteBuffer);
        fileOutputStream.close();
    }

    // 从 channel 读取
    @Test
    public void readChannelTest() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(FILE);
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        channel.read(byteBuffer);
        byteBuffer.flip();
        System.out.println(new String(byteBuffer.array(), 0, byteBuffer.limit()));
        fileInputStream.close();
    }
}
