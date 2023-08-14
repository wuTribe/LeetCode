package com.shagnguigu.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

import java.net.URI;
import java.nio.charset.StandardCharsets;


/**
 * SimpleChannelInboundHandle 是 ChannelInboundHandlerAdapter
 *
 * HttpObject 客户端和服务端互相通讯的数据被封装成 HttpObject
 *
 * Created by wuyufan on 2022/12/6.
 */
public class TestServerHandle extends SimpleChannelInboundHandler<HttpObject> {
    // 浏览器会发出两次请求，一次是目标请求，一个是访问图标
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if (msg instanceof HttpRequest) {
            System.out.println("msg.getClass() = " + msg.getClass());
            System.out.println("ctx.channel().remoteAddress() = " + ctx.channel().remoteAddress());

            // 每次请求，hashCode 都是一样，不会共享，但是如果关掉浏览器，hashCode 就会发生变化
            System.out.println("ctx.pipeline().hashCode() = " + ctx.pipeline().hashCode());
            System.out.println("this.hashCode() = " + this.hashCode());

            // 通过 URI 过滤特点资源
            HttpRequest httpRequest = (HttpRequest) msg;
            URI uri = new URI(httpRequest.uri());
            if ("/favicon.ico".equals(uri.getPath())) {
                System.out.println("请求了 /favicon.ico 不做响应");
                return;
            }

            // 回复信息给浏览器
            ByteBuf content = Unpooled.copiedBuffer("hello, 我是服务器", StandardCharsets.UTF_8);
            // 构造一个 http 的响应，即 httpResponse
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=utf-8");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            // 将构建好的 response 返回
            ctx.writeAndFlush(response);
        }
    }
}
