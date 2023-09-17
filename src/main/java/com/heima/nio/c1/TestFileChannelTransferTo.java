package com.heima.nio.c1;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * Created by wuyufan on 2023/8/11.
 */
@Slf4j
public class TestFileChannelTransferTo {
    public static void main(String[] args) {
        try (FileChannel from = new FileInputStream("data.txt").getChannel();
             FileChannel to = new FileOutputStream("to2.txt").getChannel();) {
            // 效率高，底层会利用操作系统的零拷贝优化
            // from.transferTo(0, from.size(), to);

            // 一次传输最多 2g，大文件需要多次传输
            long size = from.size();
            long left = size;
            while (left > 0) {
                left -= from.transferTo((size - left), left, to);
            }

        } catch (Exception e) {
            log.error("", e);
        }
    }
}
