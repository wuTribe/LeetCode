package com.netty.heima.nio.c4;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * Created by wuyufan on 2023/8/14.
 */
@Slf4j
public class Client2 {
    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("localhost", 8888));
        sc.write(Charset.defaultCharset().encode("1234567890asd"));
        log.debug("waiting....");
        System.in.read();
    }
}
