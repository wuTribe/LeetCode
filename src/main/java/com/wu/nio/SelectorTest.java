package com.wu.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 选择器
 *
 * Created by wuyufan on 2022/10/31.
 */
public class SelectorTest {
    // 聚合了多个选择器，可以同事并发处理多个客户端连接
    // 有一个事件循环的线程，线程通常将非阻塞IO的空闲事件用于在其他通道上进行IO操作
    // 读写操作非阻塞



    @Test
    public void nioServer() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        Selector selector = Selector.open();

        // 通道绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        // 设置通道非阻塞
        serverSocketChannel.configureBlocking(false);
        // 把通道注册到 selector 中，设置关心的事件为 OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 循环等待客户端连接
        while (true) {
        }
    }
}











