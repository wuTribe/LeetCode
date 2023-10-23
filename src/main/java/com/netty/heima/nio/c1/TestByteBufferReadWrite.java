package com.netty.heima.nio.c1;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

/**
 * Created by wuyufan on 2023/8/11.
 */
@Slf4j
public class TestByteBufferReadWrite {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 0x61);
        ByteBufferUtil.debugAll(buffer);
        buffer.put(new byte[] {0x62, 0x63, 0x64});
        ByteBufferUtil.debugAll(buffer);

        // log.debug("" + buffer.get());

        buffer.flip();
        log.debug("" + buffer.get());
        ByteBufferUtil.debugAll(buffer);

        buffer.compact();
        ByteBufferUtil.debugAll(buffer);

    }
}
