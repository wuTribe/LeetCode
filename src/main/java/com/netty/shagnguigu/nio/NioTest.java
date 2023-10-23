package com.netty.shagnguigu.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * 选择器
 * <p>
 * Created by wuyufan on 2022/10/31.
 */
public class NioTest {
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
            if (selector.select(1000) == 0) {
                System.out.println("服务器等待一秒，无连接");
                continue;
            }
            // 1. 如果返回 set.size() > 0，说明获取到关注的时间
            // 2. selector.selectedKeys() 返回关注事件的集合，通过 selectionKeys 反向获取通道
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();

                if (key.isAcceptable()) { // 连接事件
                    // accept 方法是阻塞的，但是 if 判断保证了已经准备好了，所以 accept 的不用等待
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("客户端连接成功，生成了一个 socketChannel " + socketChannel.hashCode());
                    // 将 SocketChannel 设置非阻塞
                    socketChannel.configureBlocking(false);
                    // 将 channel 注册到 selector 中，关注读事件，同时给 channel 关联一个 buffer
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }

                if (key.isReadable()) { // 读事件
                    // 通过 key 反向获取 channel
                    SocketChannel channel = (SocketChannel) key.channel();
                    // 获取这个 channel 关联的 buffer
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    channel.read(buffer);
                    System.out.println("from 客户端 " + new String(buffer.array()));
                }
                // 手动从集合中删除当前 key，防止重复操作
                keyIterator.remove();
            }
        }
    }

    @Test
    public void nioClient() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);

        // 连接服务器
        if (!socketChannel.connect(inetSocketAddress)) {
            while (!socketChannel.finishConnect()) {
                System.out.println("连接需要时间，客户端不会阻塞，可以做其他工作");
            }
        }

        String str = "hello, 小陈";
        ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes(StandardCharsets.UTF_8));
        socketChannel.write(byteBuffer);
        System.in.read();
    }
}











