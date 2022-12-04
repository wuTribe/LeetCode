package com.wu.netty;

import com.wu.netty.handler.NettyServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        // 创建 BoosGroup 和 WorkerGroup
        // 创建两个线程组 boosGroup 和 workerGroup
        // boosGroup 只是处理连接请求，真正和客户端业务处理的会交给 workerGroup 完成
        // 这两个都是无限循环
        // 默认循环线程数 = CPU 核数 * 2
        // 这就是IO密集型，线程数的去定方式：理论上是 核数 * 2，实际还可以是 核数 * (1 - 阻塞系数: < 1, 比如 0.2)
        EventLoopGroup boosGroup = new NioEventLoopGroup(1);
        // 这里给了 24 个线程，有新的客户端过来时，从 0-23 循环给客户端连接
        // 每个子线程都有自己的 selector
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {

            // 创建服务器端的启动对象，配置参数
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap
                    // 设置两个线程组
                    .group(boosGroup, workerGroup)
                    // 使用这个通道的实现
                    .channel(NioServerSocketChannel.class)
                    // 设置线程队列得到的连接个数
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 设置保持活动连接状态
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    // 给 worker group 的 event loop 设置处理的管道
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        // 向 pipline 设置处理器
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            System.out.println("客户 socketChannel hashCode= " + ch.hashCode());
                            // 可以使用一个集合管理 SocketChannel，在推送消息的时候，可以将业务加入到各个 channel 对应的
                            // NIOEventLoop 的 taskQueue 或者 scheduleTaskQueue
                            ch.pipeline().addLast(new NettyServerHandler());
                        }
                    });

            System.out.println("服务器准备好了。。。");

            // 涉及到异步模型
            // 绑定一个端口并同步，生成一个 ChannelFuture 对象
            // 启动服务器
            ChannelFuture cf = serverBootstrap.bind(6668).sync();

            //  给 cf 注册监听器，监控我们关心的事件
            // future-listener 机制，相对传统的阻塞IO，执行IO操作后线程会被阻塞住，直到操作完成
            // 异步处理的好处是不会造成线程阻塞，线程在IO操作可以执行别的程序，在高并发情形下会更加稳定和有更高的吞吐量
            cf.addListener((ChannelFutureListener) channelFuture -> {
               if (cf.isSuccess()) {
                   System.out.println("监听端口 6668 成功");
               } else {
                   System.out.println("监听端口 6668 失败");
               }
            });

            // 对关闭通道进行监听
            cf.channel().closeFuture().sync();
        } finally {
            // 优雅的关闭
            workerGroup.shutdownGracefully();
            boosGroup.shutdownGracefully();
        }

    }
}
