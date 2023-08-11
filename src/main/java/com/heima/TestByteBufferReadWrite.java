package com.heima;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

import static com.heima.ByteBufferUtil.debugAll;

/**
 * Created by wuyufan on 2023/8/11.
 */
@Slf4j
public class TestByteBufferReadWrite {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 0x61);
        debugAll(buffer);
        buffer.put(new byte[] {0x62, 0x63, 0x64});
        debugAll(buffer);

        // log.debug("" + buffer.get());

        buffer.flip();
        log.debug("" + buffer.get());
        debugAll(buffer);

        buffer.compact();
        debugAll(buffer);

    }
}
