package com.heima.netty.c5;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloWorldClient {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            send();
        }
        log.debug("finish....");
    }

    /**
     * 短链接解决粘包问题，处理完一次连接断开一次，不能解决半包问题
     */
    private static void send() {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.group(group);
            bootstrap.handler(new ChannelInboundHandlerAdapter() {
                // 会在连接建立成功后，触发 active 事件️
                @Override
                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                    ByteBuf buf = ctx.alloc().buffer(16);
                    buf.writeBytes(new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 18});
                    ctx.writeAndFlush(buf);
                    ctx.close();
                }
            });
            ChannelFuture sync = bootstrap.connect("127.0.0.1", 8080).sync();
            sync.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("", e);
        } finally {
            group.shutdownGracefully();
        }
    }
}
