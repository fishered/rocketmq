package org.apache.rocketmq.example.mmap;

import org.checkerframework.checker.units.qual.A;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8999);

        while (true){
            Socket socket = serverSocket.accept();

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            AtomicInteger integer = new AtomicInteger(0);

            try {
                byte[] buffer = new byte[1024];

                while (true){
                    int read = dataInputStream.read(buffer, 0, buffer.length);
                    integer.addAndGet(read);

                    if (read == -1){
                        System.out.println("接收：" + integer.get());
                        integer = null;
                        break;
                    }
                }
            } catch (IOException e) {
               e.printStackTrace();
            }

        }
    }

}
