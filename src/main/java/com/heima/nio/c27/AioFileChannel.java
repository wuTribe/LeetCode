package com.heima.nio.c27;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static com.heima.nio.c1.ByteBufferUtil.debugAll;

/**
 * 卡 bug
 * <p>
 * ava.nio.channels.AsynchronousCloseException: null
 * at sun.nio.ch.SimpleAsynchronousFileChannelImpl$2.run(SimpleAsynchronousFileChannelImpl.java:326)
 * <p>
 * Created by wuyufan on 2023/9/15.
 */
@Slf4j
public class AioFileChannel {
    public static void main(String[] args) throws IOException {
        try (AsynchronousFileChannel channel = AsynchronousFileChannel.open(Paths.get("words.txt"), StandardOpenOption.READ)) {
            // 参数 1:ByteBuffer
            // 参数 2:读取的起始位置
            // 参数 3:附件
            // 参数 4:回调对象
            ByteBuffer buffer = ByteBuffer.allocate(16);
            log.debug("read begin...");
            channel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override // read 成功
                public void completed(Integer result, ByteBuffer attachment) {
                    log.debug("read completed...");
                    attachment.flip();
                    debugAll(attachment);
                }

                @Override // read 失败
                public void failed(Throwable exc, ByteBuffer attachment) {
                    log.error("", exc);
                }
            });
            log.debug("read end...");
            // 此行代码有两个作用
            // 1 避免主线程退出导致守护线程退出
            // 2 try-catch-resource 完毕导致流关闭，进而导致子线程使用流时意外报错：
            // 2.2 https://stackoverflow.com/questions/43835486/java-nio-channels-asynchronousclose-exception-on-java-8
            System.in.read();
        } catch (Exception e) {
            log.error("", e);
        }
    }
}
