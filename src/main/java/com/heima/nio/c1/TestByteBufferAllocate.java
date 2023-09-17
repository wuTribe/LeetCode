package com.heima.nio.c1;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

/**
 * Created by wuyufan on 2023/8/11.
 */
@Slf4j
public class TestByteBufferAllocate {
    public static void main(String[] args) {
        // HeapByteBuffer   堆内存，读写效率低，受到 GC 影响
        log.debug("" + ByteBuffer.allocate(16).getClass());
        // DirectByteBuffer  直接内存，读写效率高，少一次拷贝，分配效率低，容易内存泄露
        log.debug("" + ByteBuffer.allocateDirect(16).getClass());
    }
}
