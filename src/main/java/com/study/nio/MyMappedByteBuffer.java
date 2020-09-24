package com.study.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MyMappedByteBuffer {

    //public static void main(String[] args) {
    //    ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024 * 10);
    //    FileInputStream fis = null;
    //    FileOutputStream fos = null;
    //    FileChannel inChannel = null;
    //    FileChannel outChannel = null;
    //    try {
    //        fis = new FileInputStream("d://testdata/data.txt");
    //        fos = new FileOutputStream("d://testdata/outdata2.txt");
    //        outChannel = fos.getChannel();
    //        inChannel = fis.getChannel();
    //        long startTime = System.currentTimeMillis();
    //        // 读取
    //       // int result = inChannel.read(byteBuffer);
    //        MappedByteBuffer inMapped = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
    //        System.out.println("文件大小：" + inChannel.size() / 1024 + "KB");
    //        long endTime = System.currentTimeMillis();
    //        System.out.println("读取文件花费：" + (endTime - startTime) + "ms");
    //        startTime = System.currentTimeMillis();
    //
    //        // 写入
    //        inMapped.flip();
    //        MappedByteBuffer outMapped = outChannel.map(FileChannel.MapMode.PRIVATE, 0, inChannel.size());
    //        for (int i = 0; i < inChannel.size(); i++) {
    //            byte b = inMapped.get(i);
    //            outMapped.put(i, b);
    //        }
    //
    //        //  outChannel.transferFrom(inChannel, 0, inChannel.size());
    //
    //        //for (int i = 0; i < inChannel.size(); i++) {
    //        //    byte b = byteBuffer.get(i);
    //        //    bytes[i] = b;
    //        //    fos.write(bytes);
    //        //}
    //        endTime = System.currentTimeMillis();
    //        System.out.println("写入文件花费：" + (endTime - startTime) + "ms");
    //
    //    } catch (FileNotFoundException e) {
    //        e.printStackTrace();
    //    } catch (IOException e) {
    //        e.printStackTrace();
    //    } finally {
    //        try {
    //            fos.close();
    //            fis.close();
    //            outChannel.close();
    //            inChannel.close();
    //        } catch (IOException e) {
    //            e.printStackTrace();
    //        }
    //
    //    }
    //
    //}

    public static void main(String[] args) {
        String srcFile = "d://testdata/data.txt";
        String destFile = "d://testdata/outdata.txt";
        RandomAccessFile rafi = null;
        RandomAccessFile rafo = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        long start = 0;
        try {
            rafi = new RandomAccessFile(srcFile, "r");
            rafo = new RandomAccessFile(destFile, "rw");
            inChannel = rafi.getChannel();
            outChannel = rafo.getChannel();
            long size = inChannel.size();
            MappedByteBuffer inMapped = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, size);
            MappedByteBuffer outMapped = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, size);
            start = System.currentTimeMillis();
            for (int i = 0; i < size; i++) {
                byte b = inMapped.get(i);
                outMapped.put(i, b);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            inChannel.close();
            outChannel.close();
            rafi.close();
            rafo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Spend: " + (double) (System.currentTimeMillis() - start) / 1000 + "s");
    }
}
