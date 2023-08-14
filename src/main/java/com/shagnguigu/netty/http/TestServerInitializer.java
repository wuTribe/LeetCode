package com.shagnguigu.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * Created by wuyufan on 2022/12/6.
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        // 得到管道
        ChannelPipeline pipeline = socketChannel.pipeline();
        // 加入一个 netty 提供的 httpServerCodec
        // 这是 netty 提供的处理 http 的编解码器
        pipeline.addLast("MyHttpServerCodec", new HttpServerCodec());
        // 增加自定义的 handle
        // Channel 包含了一个 ChannelPipeLine，而 ChannelPipeLine 中维护了一个由 ChannelHandlerContext 组成的双向链表
        // ChannelHandlerContext 中关联了一个 ChannelHandler
        // 入栈事件和出栈事件在一个双向链表中
        // 入栈事件会从链表 head 往后传递到最后一个入栈的 Handler。出栈事件会从链表 tail 往前传递到最前一个出栈的 Handler
        // 客户端往服务器走是出栈，服务端往客户端走是入栈
        pipeline.addLast("MyTestServerHandle", new TestServerHandle());
        System.out.println("@@@@@@@@ OK @@@@@@@@");
    }
}
