package com.netty.shagnguigu.nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static com.netty.shagnguigu.Constant.FILE;
import static com.netty.shagnguigu.Constant.CHANNEL_2_OUT_FILE;

/**
 * Created by wuyufan on 2022/10/20.
 */
public class FileCopyByBufferTest {

    @Test
    public void fileCopyTest() throws IOException {
        fileCopy();
    }

    public void fileCopy() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(FILE);
        FileChannel fileInputStreamChannel = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream(CHANNEL_2_OUT_FILE);
        FileChannel fileOutputStreamChannel = fileOutputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (fileInputStreamChannel.read(buffer) != -1) {
            buffer.flip();
            fileOutputStreamChannel.write(buffer);
            // 注意复位 - 清空上次的数据 - 不写会死循环 （position == limit，会导致读不到数据）
            buffer.clear();
        }

        fileInputStream.close();
        fileOutputStream.close();
    }
}
