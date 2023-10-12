package com.heima.netty.c3;

import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

/**
 * Created by wuyufan on 2023/10/12.
 */
@Slf4j
public class TestNettyPromise {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        EventLoop loop = group.next();

        // 可以主动创建 promise，结果容器
        DefaultPromise<Integer> promise = new DefaultPromise<>(loop);

        new Thread(() -> {
            log.debug("开始计算....");
            try {
                int i = 1 / 0;
                Thread.sleep(1000);
                promise.setSuccess(50);
            } catch (Exception e) {
                // log.error("", e);
                promise.setFailure(e); // 能塞异常
            }
        }).start();

        // 设置接收结果的线程
        log.debug("等待结果....");
        log.debug("结果是：" + promise.get());
    }
}
