package com.heima.c27;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

import static com.heima.c1.ByteBufferUtil.debugAll;
import static com.heima.c1.ByteBufferUtil.debugRead;

/**
 * selector 处理 accept
 * selector 处理 cancel（死循环）
 * selector 处理 read
 * selector remove key（空指针）
 * selector 客户端正常断开发生的情况（死循环）
 * selector 消息边界（半包粘包），边界超出
 * | ------ 消息边界有三种思路
 * | ------ ------ 1. 固定消息长度，数据包大小一样，服务器按照一定长度读取，缺点是浪费带宽
 * | ------ ------ 2. 按照分隔符分割，需要逐个识别，效率低
 * | ------ ------ 3. TLV 格式，     T：type类型（文本类型 context-type）      L：length 文本长度      V：value 数据
 * | ------ ------ ------ 在类型和长度已知的情况下，就可以方便获取消息大小，分配合适的 buffer，缺点是 buffer 需要提前分配，如果内容过大，影响 server 吞吐量
 * | ------ ------ ------ http 1.1 是 TLV 格式
 * | ------ ------ ------ http 2.0 是 LTV 格式
 *
 *
 *  ByteBuffer 大小分配：
 *  | ---- 每个 channel都需要记录可能被切分的消息，因为 ByteBuffer 不能被多个 channel 共同使用，因为需要为每个 channel 维护一个独立的 ByteBuffer
 *  | ---- ByteBuffer 不能太大，比如一个 ByteBuffer 1MB，如果需要支持百万连接就需要 1TB 内存，因此需要涉及大小可变的 ByteBuffer
 *  | ---- ---- 第一种思路：先分配一个比较小的 buffer，后面再 n 倍扩容。优点消息连续，缺点耗费性能
 *  | ---- ---- 第二种思路：新分配的 buffer 使用链表串联，缺点存储不连续消息解析复杂，优点避免了拷贝引起的性能损耗
 *
 *
 * Created by wuyufan on 2023/8/11.
 */
@Slf4j
public class Server {
    public static void main(String[] args) throws IOException {

        // 1. 创建 selector，管理多个 channel
        Selector selector = Selector.open();

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        // 2. 建立 selector 和 channel 的联系（注册）
        // selectionKey 就是将来事件发生后，通过他可以知道时间和那个 channel 的时间（管理员）
        // accept 事件：会在有连接请求时触发
        // connect 事件：是客户端建立连接后触发
        // read 事件：可读时间
        // write 事件：可写事件
        SelectionKey sscKey = ssc.register(selector, 0, null);
        sscKey.interestOps(SelectionKey.OP_ACCEPT); // key 只关注 accept 事件，可以注册多个事件，注意做区分
        log.debug("register key:{}", sscKey);

        ssc.bind(new InetSocketAddress(8080));

        while (true) {
            // 3. select 方法，如果没有事件发生，线程阻塞，上面四个事件其中之一发生，线程恢复运行
            // select 在事件为处理时，不会阻塞。所以当没有对事件进行处理时，会默认为未被处理，继续循环，可以调用 key.cancel 取消事件
            selector.select();
            // 4. 处理事件，selectedKeys 内部包含了所有发生的事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove(); // 迭代器 selectedKeys 容器需要手动处理完成的 key（删除），否则返回 null 或 0
                log.debug("key: {}", key);
                if (key.isAcceptable()) { // accept 事件
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel(); // 返回的 SelectableChannel 是 SelectableChannel 的父类型
                    SocketChannel sc = channel.accept();
                    sc.configureBlocking(false);
                    ByteBuffer buffer = ByteBuffer.allocate(16); // 把 buffer 作为附件关联到 key 上
                    SelectionKey scKey = sc.register(selector, 0, buffer);
                    scKey.interestOps(SelectionKey.OP_READ); // 关注 socketChannel 的 read 事件
                    log.debug("{}", sc);
                } else if (key.isReadable()) { // read 事件
                    try {
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        if (channel.read(buffer) == -1) { // 如果是正常断开，返回值为 -1
                            key.cancel();
                            continue;
                        }
                        // buffer.flip();
                        // debugRead(buffer);
                        split(buffer);
                        if (buffer.position() == buffer.limit()) {
                            ByteBuffer newBuffer = ByteBuffer.allocate(buffer.capacity() * 2);
                            buffer.flip();
                            newBuffer.put(buffer);
                            key.attach(newBuffer); // 可以关联新的附件，如扩容后的 buffer
                        }
                    } catch (IOException e) {
                        log.error("", e);
                        key.cancel(); // 异常了，取消事件，不用再继续监听
                    }
                }
            }
        }
    }


    private static void split(ByteBuffer source) {
        source.flip();
        for (int i = 0; i < source.limit(); i++) {
            // 找到一条完整消息
            if (source.get(i) == '\n') {
                int capacity = i + 1 - source.position();
                ByteBuffer target = ByteBuffer.allocate(capacity);
                for (int j = 0; j < capacity; j++) { // 从 source 向 target 写
                    target.put(source.get());
                }
                debugAll(target);
            }
        }
        source.compact();
    }
}
