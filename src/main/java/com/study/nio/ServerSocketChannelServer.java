package com.study.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;

public class ServerSocketChannelServer {

    // 用于检测所有Channel状态的Selector
    private Selector selector;
    // SocketChannel是客户端用来接收数据的，而ServerSocketChannel是服务端用来发送数据的（一般来说）
    private ServerSocketChannel serverSocketChannel;

    public static void main(String[] args) {
        try {
            new ServerSocketChannelServer().init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init() throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1", 30000));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (selector.select() > 0) {
            for (SelectionKey selectionKey : selector.selectedKeys()) {
                selector.selectedKeys().remove(selectionKey);
                // 如果selectionKey对用的通道包含客户端的连接请求
                if (selectionKey.isAcceptable()) {
                    // 调用accept()方法接受连接，产生服务器端对应的SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }
                // 如果sk对应的通道有数据需要读取
                if (selectionKey.isReadable()) {
                    // 获取该SelectionKey对应的Channel，该Channel中有可读的数据
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    // 定义准备执行读取数据的ByteBuffer
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    StringBuffer content = new StringBuffer("");
                    // 开始读取数据
                    try {
                        while (socketChannel.read(byteBuffer) > 0) {
                            byteBuffer.flip();
                            content = content.append(StandardCharsets.UTF_8.decode(byteBuffer));
                        }
                        System.out.println("====收到消息====" + content.toString());
                        // 如果捕捉到该sk对应的Channel出现了异常，即表示该Channel对应的Client出现了问题，所以从Selector中取消sk的注册
                    } catch (IOException e) {
                        selectionKey.cancel();
                        if (selectionKey.channel() != null) {
                            selectionKey.channel().close();
                        }
                    }
                    // 如果content的长度大于0，即聊天信息不为空
                    if (content.length() > 0) {
                        // 遍历该selector里注册的所有SelectKey
                        for (SelectionKey key : selector.keys()) {
                            // 获取该key对应的Channel
                            Channel targetChannel = key.channel();
                            // 如果该channel是SocketChannel对象
                            if (targetChannel instanceof SocketChannel) {
                                // 将读到的内容写入该Channel中
                                SocketChannel dest = (SocketChannel) targetChannel;
                                dest.write(StandardCharsets.UTF_8.encode(content.toString()));
                            }
                        }
                    }
                }
            }

        }
    }
}
