package com.netty.heima.netty.c3;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
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
public class EventLoopClient {
    public static void main(String[] args) throws InterruptedException {
        Channel channel = new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    // init 方法同样要等连接建立之后才会执行
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                })
                .connect(new InetSocketAddress("localhost", 8080))
                // 阻塞方法，等与客户端连接建立完才往下运行，此时能拿到 channel 对象，而后可用发送数据
                .sync()
                // 代表连接对象
                .channel();
        System.out.println(channel);
        // 断点给客户端发送数据，注意！idea 默认断点方式是 all，会暂停其他所有线程，会导致数据无法发送到服务器
        // 需要将断点调整为 Thread 只停止当前线程
        System.out.println("");
    }
}
