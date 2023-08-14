package com.shagnguigu.zeroCopy.oldIO;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class OldIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(7001);
        while (true) {
            Socket socket = serverSocket.accept();
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            byte[] bytes = new byte[4096];
            try {
                while (dataInputStream.read(bytes, 0, bytes.length) != -1) ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
