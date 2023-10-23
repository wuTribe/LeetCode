package com.netty.heima.netty.c3;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * inbound 是从头部开始找
 * outbound 是从尾部开始找
 *
 * outbound 需要往 channel 中写出数据才会执行
 *
 * nio 中会默认设定 head 和 tail，往 pipline 中加入 handle 都会在 head 和 tail 中
 * head -> h1 -> h2 -> h3 -> h4 -> h5 -> h6 -> tail
 *
 * Created by wuyufan on 2023/10/13.
 */
@Slf4j
public class TestPipeline {
    public static void main(String[] args) {
        new ServerBootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast("h1", new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        log.debug("1");
                                        ByteBuf buf = (ByteBuf) msg;
                                        String name = buf.toString(Charset.defaultCharset());
                                        super.channelRead(ctx, name);  // 也会传递 handle，如果不调用，链条会断开
                                    }
                                })
                                .addLast("h2", new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object name) throws Exception {
                                        log.debug("2");
                                        Stu stu = new Stu(name.toString());
                                        super.channelRead(ctx, stu);
                                    }
                                })
                                .addLast("h3", new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object stu) throws Exception {
                                        log.debug("3, 结果：{}， {}", stu, stu.getClass());
                                        super.channelRead(ctx, stu);
                                        ch.writeAndFlush(ctx.alloc().buffer().writeBytes("server...".getBytes()));
                                        // 下面这个方法是从当前的 handle 往 head 找出栈 handle
                                        // ctx.writeAndFlush(ctx.alloc().buffer().writeBytes("server...".getBytes()));
                                    }
                                })
                                .addLast("h4", new ChannelOutboundHandlerAdapter() {
                                    @Override
                                    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                        log.debug("4");
                                        super.write(ctx, msg, promise);
                                    }
                                })
                                .addLast("h5", new ChannelOutboundHandlerAdapter() {
                                    @Override
                                    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                        log.debug("5");
                                        super.write(ctx, msg, promise);
                                    }
                                })
                                .addLast("h6", new ChannelOutboundHandlerAdapter() {
                                    @Override
                                    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                        log.debug("6");
                                        super.write(ctx, msg, promise);
                                    }
                                });
                    }
                })
                .bind(8080);
    }

    @Data
    @AllArgsConstructor
     static class Stu {
        private String name;
    }
}
