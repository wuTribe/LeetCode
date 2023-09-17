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
public class WriteServer2 {
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
                    SelectionKey scKey = sc.register(selector, 0, null);
                    scKey.interestOps(SelectionKey.OP_READ);

                    // 1. 向客户端发送大量数据
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < 30000000; i++) {
                        sb.append("a");
                    }
                    ByteBuffer byteBuffer = Charset.defaultCharset().encode(sb.toString());

                    // 2. 返回值表示实际写入的字节数
                    int write = sc.write(byteBuffer);
                    log.debug(" 正在写出的字节数 {} ", write);

                    // 3. 判断是否有剩余内容
                    if (byteBuffer.hasRemaining()) {
                        // 4. 关注可写事件  1    4
                        scKey.interestOps(scKey.interestOps() + SelectionKey.OP_WRITE);
                        // scKey.interestOps(scKey.interestOps() | SelectionKey.OP_WRITE);
                        // 5. 把没写完的数据挂到 scKey 上
                        scKey.attach(byteBuffer);
                    }
                } else if (key.isWritable()) {
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    SocketChannel sc = (SocketChannel) key.channel();
                    int write = sc.write(buffer);
                    log.debug("后续正在写出的字节数 {}", write);
                    // 6. 清理数据
                    if (!buffer.hasRemaining()) {
                        key.attach(null);
                        key.interestOps(key.interestOps() - SelectionKey.OP_WRITE); // 不需要再关注可写事件
                    }
                }
            }
        }
    }
}
