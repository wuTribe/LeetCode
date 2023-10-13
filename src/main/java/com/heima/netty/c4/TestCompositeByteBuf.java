package com.heima.netty.c4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by wuyufan on 2023/10/14.
 */
@Slf4j
public class TestCompositeByteBuf {
    public static void main(String[] args) {
        ByteBuf buf1 = ByteBufAllocator.DEFAULT.buffer();
        buf1.writeBytes(new byte[] {1, 2, 3, 4, 5});

        ByteBuf buf2 = ByteBufAllocator.DEFAULT.buffer();
        buf2.writeBytes(new byte[] {6, 7, 8, 9, 10});

        // 会拷贝，多了影响性能
        // ByteBuf newBuf = ByteBufAllocator.DEFAULT.buffer();
        // newBuf.writeBytes(buf1).writeBytes(buf2);


        CompositeByteBuf compositeBuffer = ByteBufAllocator.DEFAULT.compositeBuffer();
        compositeBuffer.addComponents(true, buf1, buf2);
        TestByteBuf.log(compositeBuffer);


    }
}
