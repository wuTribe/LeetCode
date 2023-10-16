package com.heima.netty.c4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Created by wuyufan on 2023/10/16.
 */
public class TestLengthFieldDecoder {
    public static void main(String[] args) {
        //          +-------------------------------------------------+
        //          |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
        // +--------+-------------------------------------------------+----------------+
        // |00000000| 00 00 00 0c 48 65 6c 6c 6f 2c 20 77 6f 72 6c 64 |....Hello, world|
        // +--------+-------------------------------------------------+----------------+
        // 18:03:59 [DEBUG] [main] i.n.h.l.LoggingHandler - [id: 0xembedded, L:embedded - R:embedded] READ: 7B
        //          +-------------------------------------------------+
        //          |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
        // +--------+-------------------------------------------------+----------------+
        // |00000000| 00 00 00 03 48 69 21                            |....Hi!         |
        // +--------+-------------------------------------------------+----------------+
        EmbeddedChannel channel = new EmbeddedChannel(
                new LengthFieldBasedFrameDecoder(1024, 0, 4, 0, 0),
                new LoggingHandler(LogLevel.DEBUG)
        );

        //          +-------------------------------------------------+
        //          |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
        // +--------+-------------------------------------------------+----------------+
        // |00000000| 48 65 6c 6c 6f 2c 20 77 6f 72 6c 64             |Hello, world    |
        // +--------+-------------------------------------------------+----------------+
        // 18:03:28 [DEBUG] [main] i.n.h.l.LoggingHandler - [id: 0xembedded, L:embedded - R:embedded] READ: 3B
        //          +-------------------------------------------------+
        //          |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
        // +--------+-------------------------------------------------+----------------+
        // |00000000| 48 69 21                                        |Hi!             |
        // +--------+-------------------------------------------------+----------------+
        // EmbeddedChannel channel = new EmbeddedChannel(
        //         new LengthFieldBasedFrameDecoder(1024, 0, 4, 0, 4),
        //         new LoggingHandler(LogLevel.DEBUG)
        // );

        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        send(buffer, "Hello, world");
        send(buffer, "Hi!");
        channel.writeInbound(buffer);
    }

    private static void send(ByteBuf buffer, String content) {
        byte[] bytes = content.getBytes();
        int length = bytes.length;
        buffer.writeInt(length);
        buffer.writeBytes(bytes);
    }
}
