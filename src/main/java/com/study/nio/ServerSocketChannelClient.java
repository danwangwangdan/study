package com.study.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ServerSocketChannelClient {

    // 用于检测所有channel状态的selector
    private Selector selector;
    // SocketChannel是客户端用来接收数据的，而ServerSocketChannel是服务端用来发送数据的（一般来说）
    private SocketChannel socketChannel;
    // 等待channel的超时时间
    private long timeout = 10;
    // 客户端线程运行标志
    private boolean runFlag = true;

    public static void main(String[] args) {
        new ServerSocketChannelClient().connect();
    }

    public void connect() {
        try {
            // 打开一个selector
            selector = Selector.open();
            // 打开一个channel
            socketChannel = SocketChannel.open();
            // 设置为非阻塞模式
            socketChannel.configureBlocking(false);
            // 绑定到 目的地址
            SocketChannel.open(new InetSocketAddress("127.0.0.1", 30000));
            // 将该channel注册到selector，并在注册过程中指出该channel已准备好 读取；只有非阻塞channel才能注册
            socketChannel.register(selector, SelectionKey.OP_READ);
            // 启动客户端接收数据的线程
            new ClientThread().start();

            // 创建键盘输入流
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                // 读取键盘输入
                String line = scanner.nextLine();
                // 将键盘输入的内容输出到SocketChannel中
                socketChannel.write(StandardCharsets.UTF_8.encode(line));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    class ClientThread extends Thread {
        @Override
        public void run() {
            while (runFlag) {
                try {
                    // 等待数据的到来，一直等待通道进入就绪状态
                    if (selector.select(timeout) == 0) {
                        continue;
                    }
                    // 遍历每个可用IO操作Channel对应的SelectionKey，这些channel都是 已准备就绪的
                    for (SelectionKey selectionKey : selector.selectedKeys()) {

                        // ？？移除已处理的selectionKey，以保证它下次准备就绪时能够继续使用
                        selector.selectedKeys().remove(selectionKey);
                        // 如果该selectionKey对应的Channel有可读的数据
                        if (selectionKey.isReadable()) {
                            // 使用NIO读取Channel中的数据
                            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            StringBuffer content = new StringBuffer("");
                            while (socketChannel.read(byteBuffer) > 0) {
                                socketChannel.read(byteBuffer);
                                byteBuffer.flip();
                                content = content.append(StandardCharsets.UTF_8.decode(byteBuffer));
                            }
                            System.out.println("接收到信息：" + content);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }
    }
}


