package com.heima.nio.c1;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

/**
 * Created by wuyufan on 2023/8/11.
 */
@Slf4j
public class TestByteBufferExam {
    public static void main(String[] args) {
        ByteBuffer source = ByteBuffer.allocate(32);
        ByteBufferUtil.debugAll(source);
        source.put("Hello,world\nI'm zhangsan\nHo".getBytes());
        ByteBufferUtil.debugAll(source);
        split(source);
        source.put("w are you?\n".getBytes());
        ByteBufferUtil.debugAll(source);
        split(source);
    }

    private static void split(ByteBuffer source) {
        source.flip();
        ByteBufferUtil.debugAll(source);
        for (int i = 0; i < source.limit(); i++) {
            // 找到一条完整消息
            if (source.get(i) == '\n') {
                int capacity = i + 1 - source.position();
                ByteBuffer target = ByteBuffer.allocate(capacity);
                for (int j = 0; j < capacity; j++) { // 从 source 向 target 写
                    log.debug(" ------------- get : {} ---------------- ", j);
                    target.put(source.get());
                }
                // debugAll(target);
            }
        }
        log.debug(" <<<<<<<<<<<<<<<<<<<<<<<<<<<<<< ");
        ByteBufferUtil.debugAll(source);
        source.compact();
        ByteBufferUtil.debugAll(source);
        log.debug(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
    }
}
