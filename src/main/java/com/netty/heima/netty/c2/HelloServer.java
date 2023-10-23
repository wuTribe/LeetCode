package com.netty.heima.netty.c2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by wuyufan on 2023/9/17.
 */
@Slf4j
public class HelloServer {
    public static void main(String[] args) {
        // 1. 服务器端启动器：负责组装 netty 组建，协同工作，启动服务器
        new ServerBootstrap()
                // 2. BossEventLoop 和 WorkerEventLoop。（selector，Thread）
                .group(new NioEventLoopGroup())
                // 3. 选择服务器 ServerSocketChannel 实现
                .channel(NioServerSocketChannel.class)
                // 4. boss 负责连接，worker（child） 负责读写
                // 以下代码决定了 worker 能执行哪些操作（handle）
                .childHandler(
                        // 5. channel 代表和客户端进行数据读写的通道
                        // Initializer 初始化的channel，负责添加其他的 handler
                        new ChannelInitializer<NioSocketChannel>() {
                            // 6. 添加具体 handle，init 方法只有在连接建立之后才会执行，由 netty 内部的 accept 处理器调用
                            @Override
                            protected void initChannel(NioSocketChannel ch) throws Exception {
                                // 7. 将 ByteBuf 转化为字符串
                                ch.pipeline().addLast(new StringDecoder());
                                // 8. 自定义 handle
                                ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                    @Override // 读事件
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        log.debug(msg + "");
                                    }
                                });
                            }
                        })
                // 7. 绑定的监听端口
                .bind(8080);
    }
}
