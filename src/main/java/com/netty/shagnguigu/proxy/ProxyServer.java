// package com.wu.proxy;
//
// import java.io.IOException;
// import java.io.InputStream;
// import java.io.OutputStream;
// import java.net.ServerSocket;
// import java.net.Socket;
// import java.net.URL;
//
// /**
//  * Created by wuyufan on 2023/7/11.
//  */
// public class ProxyServer {
//
//     private static final int PROXY_PORT = 8888; // 代理服务器监听的端口号
//
//     public static void main(String[] args) {
//         try {
//             ServerSocket serverSocket = new ServerSocket(PROXY_PORT);
//             System.out.println("Proxy server started on port " + PROXY_PORT);
//
//             // 监听客户端连接
//             while (true) {
//                 Socket clientSocket = serverSocket.accept();
//                 System.out.println("Client connected: " + clientSocket.getInetAddress());
//
//                 // 创建线程处理客户端请求
//                 Thread thread = new Thread(() -> handleClientRequest(clientSocket));
//                 thread.start();
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
//
//     private static void handleClientRequest(Socket clientSocket) {
//         try {
//             // 从客户端读取请求
//             InputStream clientInput = clientSocket.getInputStream();
//
//             // 解析请求、修改请求、处理请求等操作...
//             String targetUrl = parseTargetUrlFromRequest(clientInput);
//
//             if (targetUrl != null) {
//                 // 将请求转发给目标服务器
//                 URL url = new URL(targetUrl);
//                 Socket serverSocket = new Socket(url.getHost(), url.getPort());
//                 OutputStream serverOutput = serverSocket.getOutputStream();
//
//                 byte[] buffer = new byte[4096];
//                 int bytesRead;
//                 while ((bytesRead = clientInput.read(buffer)) != -1) {
//                     // 将从客户端读取的数据转发给目标服务器
//                     serverOutput.write(buffer, 0, bytesRead);
//                     serverOutput.flush();
//                 }
//
//                 // 从目标服务器读取响应
//                 InputStream serverInput = serverSocket.getInputStream();
//                 OutputStream clientOutput = clientSocket.getOutputStream();
//
//                 while ((bytesRead = serverInput.read(buffer)) != -1) {
//                     // 将从目标服务器读取的数据发送给客户端
//                     clientOutput.write(buffer, 0, bytesRead);
//                     clientOutput.flush();
//                 }
//
//                 // 关闭连接
//                 serverSocket.close();
//                 clientSocket.close();
//             } else {
//                 // 无法解析目标URL，关闭连接
//                 clientSocket.close();
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
//
//     private static String parseTargetUrlFromRequest(InputStream inputStream) throws IOException {
//         // 解析客户端请求并从中提取目标URL
//         // 根据实际情况，您可能需要使用适当的HTTP请求解析库或手动解析请求。
//
//         return null; // 返回目标URL，例如 "http://example.com:8080/path"
//     }
// }
//
