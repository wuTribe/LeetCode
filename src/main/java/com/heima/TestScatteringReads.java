package com.heima;

import lombok.extern.slf4j.Slf4j;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static com.heima.ByteBufferUtil.debugAll;

/**
 * Created by wuyufan on 2023/8/11.
 */
@Slf4j
public class TestScatteringReads {
    public static void main(String[] args) {
        try (FileChannel channel = new RandomAccessFile("words.txt", "r").getChannel()) {
            ByteBuffer b1 = ByteBuffer.allocate(3);
            ByteBuffer b2 = ByteBuffer.allocate(3);
            ByteBuffer b3 = ByteBuffer.allocate(5);
            channel.read(new ByteBuffer[] {b1, b2, b3});
            b1.flip();
            b2.flip();
            b3.flip();
            debugAll(b1);
            debugAll(b2);
            debugAll(b3);
        } catch (Exception e) {
            log.error("", e);
        }
    }
}
