package com.study.nio;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class MyByteBuffer {

    public static void main(String[] args) {

        //ByteBuffer bb = ByteBuffer.allocate(1024);
        ////将ByteBuffer转化为CharBuffer视图后，再调用put，ByteBuffer中的position指针不会移动
        //bb.asCharBuffer().put("Hello World");
        ////为了能正确的输出，这里改变了limit指针的位置，使之变到了字符数组的末尾
        //bb.limit("Hello World".length() * Character.BYTES);//字符数组长度*每个字符占的字节数
        //while (bb.hasRemaining()) {
        //    System.out.print(bb.getChar());
        //}

        ByteBuffer bb = ByteBuffer.allocate(1024);
        IntBuffer ib = bb.asIntBuffer();
        ib.put(new int[]{1, 42, 12, -12});
        /* 将ByteBuffer转化为IntBuffer视图后，再调用put，ByteBuffer中的position指针不会移动
         * 但是所生成的IntBuffer中的position会按正常方式移动
         * 而且整个IntBuffer的capacity会按照byte 和 int 之间的所占字节大小比例而改变
         */
        System.out.println("ByteBuffer.position = " + bb.position());
        System.out.println("ByteBuffer.limit = " + bb.limit());
        System.out.println("ByteBuffer.capacity = " + bb.capacity());
        System.out.println("IntBuffer.position = " + ib.position());
        System.out.println("IntBuffer.limit = " + ib.limit());
        System.out.println("IntBuffer.capacity = " + ib.capacity());
        ib.flip();
        while (ib.hasRemaining()) {
            System.out.println(ib.get());
        }
    }
}
