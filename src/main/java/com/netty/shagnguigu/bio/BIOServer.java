package com.netty.shagnguigu.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wuyufan on 2022/10/19.
 */
public class BIOServer {
    public static void main(String[] args) throws IOException {
        // 使用线程池
        ExecutorService pool = Executors.newCachedThreadPool();
        // 创建 ServerSocket
        System.out.println("服务端启动。。。。");
        ServerSocket serverSocket = new ServerSocket(6666);
        while (true) {
            System.out.println("===================================================");
            System.out.println("当前线程信息 id = " + Thread.currentThread().getId());
            System.out.println("当前线程信息 name = " + Thread.currentThread().getName());
            System.out.println("等待客户端连接");
            final Socket socket = serverSocket.accept();
            System.out.println("连接一个客户端。。。");
            pool.execute(() -> {
                handle(socket);
            });
            System.out.println("===================================================");
        }
    }

    private static void handle(Socket socket) {
        System.out.println("--------------------------------------------------");
        System.out.println("---- 执行线程 ----");
        System.out.println("当前线程信息 id = " + Thread.currentThread().getId());
        System.out.println("当前线程信息 name = " + Thread.currentThread().getName());
        // 和客户端通信
        byte[] bytes = new byte[1024];
        try(InputStream in = socket.getInputStream()) {
            int read;
            while ((read = in.read(bytes)) != -1) {
                System.out.println(new String(bytes, 0, read));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("--------------------------------------------------");
    }
}
