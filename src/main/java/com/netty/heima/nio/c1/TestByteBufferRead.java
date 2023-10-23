package com.netty.heima.nio.c1;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

import static com.netty.heima.nio.c1.ByteBufferUtil.debugAll;

/**
 * Created by wuyufan on 2023/8/11.
 */
@Slf4j
public class TestByteBufferRead {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[] {'a', 'b', 'c', 'd'});
        buffer.flip();

        // rewind 重复读
        // buffer.get(new byte[4]);
        // debugAll(buffer);
        // buffer.rewind();
        // log.debug("" + (char) buffer.get());
        // debugAll(buffer);

        // mark & reset 标记
        // Mark 做一个标记，记录 position 位置，reset 是将 position 重置到 mark 位置
        log.debug("" + (char) buffer.get());
        log.debug("" + (char) buffer.get());
        buffer.mark(); // 标记索引 2 的位置
        log.debug("" + (char) buffer.get());
        log.debug("" + (char) buffer.get());
        debugAll(buffer);
        buffer.reset();
        debugAll(buffer);

        // get(i)  根据索引获取，不移动指针
        log.debug("" + buffer.get(3));
        debugAll(buffer);
    }
}
