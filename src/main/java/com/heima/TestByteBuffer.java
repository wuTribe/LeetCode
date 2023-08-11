package com.heima;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by wuyufan on 2023/8/10.
 */
@Slf4j
public class TestByteBuffer {
    public static void main(String[] args) {
        try (FileChannel fileChannel = new FileInputStream("data.txt").getChannel()) {
            ByteBuffer buf = ByteBuffer.allocate(10); // 字节
            while (true) {
                // 从 channel 读，buffer 写
                int len = fileChannel.read(buf); // 返回读到的实际字节数，-1 表示末尾
                log.debug("读取到的字节数 {}", len);
                if (len == -1) {
                    break;
                }
                // 打印内容
                buf.flip(); // 切换到读模式
                while (buf.hasRemaining()) { // 是否有剩余数据
                    log.debug("实际字节 {}", (char) buf.get());
                }
                buf.clear(); // 切换回写模式
                // buf.compact(); // 切换写模式
            }
        } catch (Exception e) {
            log.error("", e);
        }
    }
}
