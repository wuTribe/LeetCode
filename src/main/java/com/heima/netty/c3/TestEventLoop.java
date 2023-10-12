package com.heima.netty.c3;

import io.netty.channel.DefaultEventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * Created by wuyufan on 2023/10/12.
 */
@Slf4j
public class TestEventLoop {
    public static void main(String[] args) {
        // 1. 创建时间循环组
        EventLoopGroup group = new NioEventLoopGroup(2); // IO 事件，普通任务，定时任务
        // EventLoopGroup group1 = new DefaultEventLoop(); // 普通任务，定时任务

        // 2. 获取下一个事件循环对象，单向循环链表
        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());

        // 3. 执行普通任务
        group.next().submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("ok");
        });


        // 4. 执行定时任务
        // 任务事件，初始延迟，每次任务延迟时间，时间单位
        group.next().scheduleAtFixedRate(() -> {
           log.debug("ok");
        }, 0, 1, TimeUnit.SECONDS);

        log.debug("main");
    }
}
