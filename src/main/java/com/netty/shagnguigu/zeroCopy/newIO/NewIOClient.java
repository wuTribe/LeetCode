package com.netty.shagnguigu.zeroCopy.newIO;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NewIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 7001));

        String fileName = "C:\\Users\\90787\\Downloads\\sysdiag-full-5.0.71.2-2022.11.11.1.exe";
        FileChannel fileChannel = new FileInputStream(fileName).getChannel();

        // 在 Linux 下一个 transferTo 方法即可完成
        // 但是在 windows 下调用一次 transferTo 方法只能发送 8M，就需要分段传输文件
        // transferTo 方法底层采用了零拷贝技术
        long startTime = System.currentTimeMillis();
        long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);
        System.out.println("发送总的字节数：" + transferCount + " 耗时：" + (System.currentTimeMillis() - startTime));
        // 关闭
        fileChannel.close();
    }
}
