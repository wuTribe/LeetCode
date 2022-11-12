package com.wu.nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import static com.wu.Constant.FILE;
import static com.wu.Constant.CHANNEL_1_OUT_FILE;

/**
 * Created by wuyufan on 2022/10/20.
 */
public class FileCopyByChannelTest {
    @Test
    public void fileCopyByChannel() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(FILE);
        FileChannel fileInputStreamChannel = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream(CHANNEL_1_OUT_FILE);
        FileChannel fileOutputStreamChannel = fileOutputStream.getChannel();

        fileOutputStreamChannel.transferFrom(fileInputStreamChannel, 0, fileInputStreamChannel.size());

        fileInputStream.close();
        fileOutputStream.close();
    }
}
