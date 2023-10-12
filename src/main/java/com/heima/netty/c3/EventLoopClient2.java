package com.heima.netty.c3;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * Created by wuyufan on 2023/9/17.
 */
@Slf4j
public class EventLoopClient2 {
    public static void main(String[] args) throws InterruptedException {
        ChannelFuture channelFuture = new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                })
                .connect(new InetSocketAddress("localhost", 8080)); // 连接不是主线程做，并且需要耗费 1s

        // 2.1 使用 sync 方法同步处理结果

        // 如果不执行这行代码，主线程不阻塞，会直接拿到 channel 发送数据，实际连接并没有建立，发送数据会失败
        // channelFuture.sync();
        // Channel channel = channelFuture.channel();
        // // 如果已经建立好连接    打印信息   [id: 0x2ddca228, L:/127.0.0.1:49784 - R:localhost/127.0.0.1:8080]
        // // 如果没建立好连接    打印信息   [id: 0x53a7215f]
        // log.debug("{}", channel);
        // channel.writeAndFlush(" hello world");


        // 2.2 使用 addListener（回调对象） 方法异步处理结果
        channelFuture.addListener(new ChannelFutureListener() {
            // nio 连接建立好之后，回调这个方法
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                Channel channel = future.channel();
                log.debug("{}", channel);
                channel.writeAndFlush("hello world");
            }
        });


    }
}
