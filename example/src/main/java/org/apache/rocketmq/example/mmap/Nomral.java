package org.apache.rocketmq.example.mmap;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class Nomral {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8999);

        String fileName = "E://workSpace//store.log";//37.8 MB (39,703,524 字节)

        InputStream inputStream = new FileInputStream(fileName);

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        try {
            byte[] buffer = new byte[1024];

            Integer read, total = 0;

            long time = System.currentTimeMillis();

            while ((read = inputStream.read(buffer)) > 0){
                total += read;
                dataOutputStream.write(buffer);
            }

            long end = System.currentTimeMillis();

            System.out.println("发送" + total + ",用时:" + ((end - time) ));
        } finally {
            dataOutputStream.close();
            socket.close();
            inputStream.close();
        }
    }

}
