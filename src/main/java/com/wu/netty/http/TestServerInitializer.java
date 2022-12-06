package com.wu.netty.http;

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
        pipeline.addLast("MyTestServerHandle", new TestServerHandle());
    }
}
