package com.wu.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 读取数据的事件
     *
     * @param ctx 上下文对象，含有管道（业务），通道（数据），地址
     * @param msg 客户端发送的数据
     * @throws Exception Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        // // nio 是 Byte Buffer
        // ByteBuf buf = (ByteBuf) msg;
        //
        // System.out.println("ctx = " + ctx);
        // System.out.println("================ channel 和 pipeline 的关系 ================");
        // // 本质是双向链表
        // System.out.println("ctx.pipeline() = " + ctx.pipeline());
        // System.out.println("buf.toString(StandardCharsets.UTF_8) = " + buf.toString(StandardCharsets.UTF_8));
        // System.out.println("ctx.channel().remoteAddress() = " + ctx.channel().remoteAddress());


        // 这里又一个非常耗费时间的业务 => 异步执行 => 提交到该 channel 对应的
        // NIOEventLoop 的 taskQueue 中
        ctx.channel().eventLoop().execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("异常" + e.getMessage());
            }
            ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端2", StandardCharsets.UTF_8));
        });
        // 执行链，所以总共三十秒才输出完
        ctx.channel().eventLoop().execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                System.out.println("异常" + e.getMessage());
            }
            ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端3", StandardCharsets.UTF_8));
        });
        // 定时执行
        ctx.channel().eventLoop().schedule(() -> {
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                System.out.println("异常" + e.getMessage());
            }
            ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端4", StandardCharsets.UTF_8));
        }, 5, TimeUnit.SECONDS);
        System.out.println("go on ...");
    }

    /**
     * 数据读取完毕
     *
     * @param ctx 上下文对象
     * @throws Exception Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 一般需要对数据进行编码再发送
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端1", StandardCharsets.UTF_8));
    }

    /**
     * 处理异常，关闭通道
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
