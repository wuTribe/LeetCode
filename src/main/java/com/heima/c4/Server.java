package com.heima.c4;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import static com.heima.c1.ByteBufferUtil.debugRead;

/**
 * Created by wuyufan on 2023/8/11.
 */
@Slf4j
public class Server {
    public static void main(String[] args) throws IOException {
        // 使用 nio 理解阻塞模式，单线程

        ByteBuffer buffer = ByteBuffer.allocate(16);
        // 1. 创建了服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false); // 非阻塞模式，影响 accept 方法

        // 2. 绑定了监听端口
        ssc.bind(new InetSocketAddress(8080));

        // 3. 连接集合
        List<SocketChannel> channels = new ArrayList<>();
        while (true) {
            // log.debug("connection....");
            SocketChannel sc = ssc.accept(); // accept 建立客户端连接 与客户端通信，阻塞方法
            if (sc != null) {
                log.debug("connected.... {}", sc);
                sc.configureBlocking(false); // 设置非阻塞模式，影响 read 方法
                channels.add(sc);
            }
            for (SocketChannel channel : channels) {
                // 接收客户端发送的数据
                // log.debug("before read.... {}", channel);
                int read = channel.read(buffer);// 阻塞方法，没有数据返回 0
                if (read > 0) {
                    buffer.flip();
                    debugRead(buffer);
                    buffer.clear();
                    log.debug("after read.... {}", channel);
                }
            }
        }
    }
}
