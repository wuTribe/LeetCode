package com.netty.heima.netty.c4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import lombok.extern.slf4j.Slf4j;

/**
 * slice 切片内存共享
 * duplicate  全量内存共享，读指针分离
 * copy  深拷贝，，不共享
 *
 * Created by wuyufan on 2023/10/14.
 */
@Slf4j
public class TestSlice {
    public static void main(String[] args) {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(10);
        buffer.writeBytes(new byte[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'});
        TestByteBuf.log(buffer);

        // 在切片中，没有发生数据复制，共享内存
        // 如果在原有的切片中写入数据，可能会影响其他切片，导致报错，因此长度会说鸥到限制
        // 如果对原来的数据进行操作，比如翻转，则会影响切片后的数据
        ByteBuf slice = buffer.slice(0, 5);
        ByteBuf slice1 = buffer.slice(5, 5);
        TestByteBuf.log(slice);
        TestByteBuf.log(slice1);


        log.debug(" ================== ");

        slice.setByte(0, 'b');
        TestByteBuf.log(slice);
        TestByteBuf.log(buffer);


        // 如果直接释放原有的内存，会导致切片后的 buf 使用时报错，可以让原有 buf 调用 retain 计数 + 1
        // 使用完后再 release

        buffer.retain();
        // buffer.release()

    }
}
