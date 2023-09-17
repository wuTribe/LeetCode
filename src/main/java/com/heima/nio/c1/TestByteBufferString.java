package com.heima.nio.c1;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * Created by wuyufan on 2023/8/11.
 */
@Slf4j
public class TestByteBufferString {
    public static void main(String[] args) {
        // 1. 字符串转 byteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(16);
        String s = "hello";
        buffer.put(s.getBytes());
        ByteBufferUtil.debugAll(buffer);

        // 2. Charset
        ByteBuffer buffer2 = StandardCharsets.UTF_8.encode(s); // 生成的 buffer，自动切换为读模式
        ByteBufferUtil.debugAll(buffer2);

        // 3. wrap
        ByteBuffer buffer3 = ByteBuffer.wrap(s.getBytes());
        ByteBufferUtil.debugAll(buffer3);


        // 4. 转字符串
        log.debug("buffer -- " + StandardCharsets.UTF_8.decode(buffer).toString());
        log.debug("buffer2 -- " + StandardCharsets.UTF_8.decode(buffer2).toString());
        log.debug("buffer3 -- " + StandardCharsets.UTF_8.decode(buffer3).toString());
    }
}
