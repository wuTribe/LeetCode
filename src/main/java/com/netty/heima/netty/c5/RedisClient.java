package com.netty.heima.netty.c5;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * Created by wuyufan on 2023/10/16.
 */
@Slf4j
public class RedisClient {
    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            ChannelFuture channelFuture = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    final byte[] LINE = {'\r', '\n'};
                                    ByteBuf buffer = ctx.alloc().buffer();
                                    // 根据 Redis 协议，往 Redis 发送数据
                                    buffer.writeBytes("*3".getBytes(StandardCharsets.UTF_8));
                                    buffer.writeBytes(LINE);
                                    buffer.writeBytes("$3".getBytes(StandardCharsets.UTF_8));
                                    buffer.writeBytes(LINE);
                                    buffer.writeBytes("set".getBytes(StandardCharsets.UTF_8));
                                    buffer.writeBytes(LINE);
                                    buffer.writeBytes("$4".getBytes(StandardCharsets.UTF_8));
                                    buffer.writeBytes(LINE);
                                    buffer.writeBytes("name".getBytes(StandardCharsets.UTF_8));
                                    buffer.writeBytes(LINE);
                                    buffer.writeBytes("$7".getBytes(StandardCharsets.UTF_8));
                                    buffer.writeBytes(LINE);
                                    buffer.writeBytes("wuyufan".getBytes(StandardCharsets.UTF_8));
                                    buffer.writeBytes(LINE);
                                    ctx.writeAndFlush(buffer);
                                }
                            });
                        }
                    })
                    .connect("127.0.0.1", 6379);
            channelFuture.sync();
            channelFuture.channel().close().sync();
        } catch (InterruptedException e) {
            log.error("", e);
        } finally {
            group.shutdownGracefully();
        }
    }
}
