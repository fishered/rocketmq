package org.apache.rocketmq.example.mmap;



import java.io.*;
import java.net.InetSocketAddress;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class Mmap {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8999));

        socketChannel.configureBlocking(true);

        String fileName = "E://workSpace//store.log";//37.8 MB (39,703,524 字节)
        FileChannel fileChannel = null;
        try {
            fileChannel = new FileInputStream(fileName).getChannel();

            long size = fileChannel.size();
            long position = 0;
            long total = 0;

            long timeMillis = System.currentTimeMillis();

            while (position < size) {
                long currentNum = fileChannel.transferTo(position, fileChannel.size(), socketChannel);
                if (currentNum <= 0) {
                    break;
                }
                total += currentNum;
                position += currentNum;
            }
            long timeMillis1 = System.currentTimeMillis();

            System.out.println("发送：" + total + ",用时："+ (timeMillis1 - timeMillis) );

        } finally {
            fileChannel.close();
            socketChannel.close();
        }

    }

}
