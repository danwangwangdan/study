package com.study.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class MyFileChannel {

    public static void main(String[] args) {
        try (FileChannel inChannel = FileChannel.open(Paths.get("src/indata.txt"), StandardOpenOption.READ);
             FileChannel outChannel = FileChannel.open(Paths.get("src/outdata.txt"), StandardOpenOption.WRITE);
        ) {
            ByteBuffer buffer = ByteBuffer.allocate(48);
            /*
             * 1.channel.write()和read()方法是需要移动position和limit指针的
             *      所以需要用buffer.flip()等方法，来保证读写正确
             * 2.channel.read()方法是从通道读取到缓冲区中，读取的字节数量是n (n是buffer中当前剩余的容量)，
             *      但是读取的数量是取决于通道的当前状态。例如：要读到文件末尾，不够buffer的容量也就是 通道剩余<=n,
             *      或者说ServerSocketChannel 当前只能读取准备好的，这很可能<n,所以说加循环，
             *      另外read的方法返回当前读取的数量，一个int 可以根据他来设定while
             *      如果返回-1，表示到了文件末尾
             */
            int bytesRead = inChannel.read(buffer);
            while (bytesRead != -1) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.println(buffer.getChar());
                    outChannel.write(buffer);
                }
                buffer.clear();
                bytesRead = inChannel.read(buffer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
