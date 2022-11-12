package com.wu.nio;

import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import static com.wu.Constant.FILE;

/**
 * Created by wuyufan on 2022/10/19.
 */
public class BasicBufferTest {
    // 简单测试
    @Test
    public void testBuffer() {
        // buffer 的使用

        // Invariants: mark <= position <= limit <= capacity
        // private int mark = -1;     // 标记
        // private int position = 0;  // 表示下一个读写的位置
        // private int limit;         // 容量的当前终点，在读写的过程中会变化
        // private int capacity;      // 容量，不可改变

        // 可以 debug 看看上面这几个值的变化
        IntBuffer intBuffer = IntBuffer.allocate(5); // 创建一个 buffer，大小为 5，可存放 5 个 int
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i * 2);
        }

        // buffer 的子类有几个基本类型的 buffer，以 IntBuffer 为例，数据实际上存储在 final int[] hb; 的属性中
        intBuffer.flip(); // 读写切换模式 -- 切换到读模式（！！）
        // public final Buffer flip() {
        //     limit = position;  // 读数据不能超过刚刚写的部分
        //     position = 0;      // 重头开始读
        //     mark = -1;
        //     return this;
        // }

        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get()); // 内部维护了一个索引，获取一次往后移动
        }
    }

    // buffer 类型化和只读
    @Test
    public void putGet() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        byteBuffer.putInt(100);
        byteBuffer.putLong(9);
        byteBuffer.putChar('好');
        byteBuffer.putShort((short) 4);

        // 取出按照顺序和类型取
        // 如果总字节数一样，不按照顺序取不会报错，但是数据有问题
        // 如果总字节数不一样，取数的时候会报错 java.nio.BufferUnderflowException
        byteBuffer.flip();
        System.out.println(" ====================== ");
        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getChar());
        System.out.println(byteBuffer.getShort());
        System.out.println(byteBuffer.getLong());
    }

    // 只读 buffer
    @Test
    public void readOnlyBuffer() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);
        for (int i = 0; i < 64; i++) {
            byteBuffer.put((byte) i);
        }
        byteBuffer.flip();
        ByteBuffer readOnlyBuffer = byteBuffer.asReadOnlyBuffer(); // 只能读取，不能写入
        System.out.println(readOnlyBuffer.getClass()); // java.nio.HeapByteBufferR

        while (readOnlyBuffer.hasRemaining()) {
            System.out.println(readOnlyBuffer.get());
        }

        // 写入会报错
        readOnlyBuffer.putInt(10); // java.nio.ReadOnlyBufferException
    }

    @Test
    public void mappedByteBuffer() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(FILE, "rw");
        FileChannel channel = randomAccessFile.getChannel();

        // 第一个参数：使用的模式
        // 第二个参数：直接修改的起始位置
        // 第三个参数：映射的内存大小
        // 可以直接修改的范围就是 [0,5)
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        map.put(0, (byte) 'H');
        map.put(3, (byte) '9');
        map.put(5, (byte) 'P'); // IndexOutOfBoundsException
        randomAccessFile.close();
        System.out.println("执行完成");
    }
}
