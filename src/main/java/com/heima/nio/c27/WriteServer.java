package com.heima.nio.c27;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * Created by wuyufan on 2023/8/15.
 */
@Slf4j
public class WriteServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        ssc.bind(new InetSocketAddress(8080));

        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    SocketChannel sc = ssc.accept(); // 因为 ssc 只有一个，所以可以从 ssc 中拿 channel，省略从 key 中拿（都准备好了从哪里拿 channel 都一样）
                    sc.configureBlocking(false);

                    // 1. 向客户端发送大量数据
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < 30000000; i++) {
                        sb.append("a");
                    }
                    ByteBuffer byteBuffer = Charset.defaultCharset().encode(sb.toString());
                    while (byteBuffer.hasRemaining()) {
                        // 2. 返回值表示实际写入的字节数
                        int write = sc.write(byteBuffer);

                        // 18:28:17 [DEBUG] [main] c.h.c.WriteServer -  正在写出的字节数 3014633
                        // 18:28:17 [DEBUG] [main] c.h.c.WriteServer -  正在写出的字节数 3014633
                        // 18:28:17 [DEBUG] [main] c.h.c.WriteServer -  正在写出的字节数 5898195
                        // 18:28:17 [DEBUG] [main] c.h.c.WriteServer -  正在写出的字节数 0
                        // 18:28:17 [DEBUG] [main] c.h.c.WriteServer -  正在写出的字节数 524284
                        // 18:28:17 [DEBUG] [main] c.h.c.WriteServer -  正在写出的字节数 0
                        // 18:28:17 [DEBUG] [main] c.h.c.WriteServer -  正在写出的字节数 0
                        // 18:28:17 [DEBUG] [main] c.h.c.WriteServer -  正在写出的字节数 0
                        // 死循环不断往客户端发送数据，收到网络带宽的限制，有时候写了 0 字节
                        // 这样会导致线程卡在了当前 channel。希望尝试转到其他事件而不是在当前事件反复尝试
                        log.debug(" 正在写出的字节数 {} ", write);
                    }
                }
            }
        }
    }
}
