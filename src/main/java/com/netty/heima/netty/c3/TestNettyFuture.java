package com.netty.heima.netty.c3;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

/**
 * Created by wuyufan on 2023/10/12.
 */
@Slf4j
public class TestNettyFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        EventLoop eventLoop = group.next();

        // 因为 execute 方法传入 Runnable 参数，无法获取返回结果
        // 注意返回的 Future 对象是 netty 的
        Future<Integer> submit = eventLoop.submit(() -> {
            log.debug("执行计算....");
            Thread.sleep(1000);
            return 10;
        });

        // 同步等结果
        // log.debug("等待结果");
        // log.debug("结果是：" + submit.get());

        submit.addListener(new GenericFutureListener<Future<? super Integer>>() {
            @Override
            public void operationComplete(Future<? super Integer> future) throws Exception {
                log.debug("接收结果：" + future.getNow());
            }
        });
    }
}
