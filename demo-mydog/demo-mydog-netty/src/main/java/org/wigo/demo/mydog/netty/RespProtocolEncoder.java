package org.wigo.demo.mydog.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

import static org.wigo.demo.mydog.netty.RespProtocolConstant.*;

/**
 * @author wuwei31
 * @since 2021/5/20
 */
public class RespProtocolEncoder extends MessageToMessageEncoder<String> {

    @Override
    protected void encode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {
        String[] command = msg.split(" ");

        ByteBufAllocator alloc = UnpooledByteBufAllocator.DEFAULT;

        // 分配一个直接内存缓冲ByteBuf
        ByteBuf byteBuf = alloc.heapBuffer(128);

        byteBuf.writeChar(ARG_NUM_PREFIX);
        byteBuf.writeInt(command.length);
        byteBuf.writeBytes(ROW_END_SUFFIX.getBytes());
        for (String s : command) {
            byteBuf.writeChar(ARG_LEN_PREFIX);

            byteBuf.writeInt(s.getBytes().length);
            byteBuf.writeBytes(ROW_END_SUFFIX.getBytes());

            byteBuf.writeBytes(s.getBytes());
            byteBuf.writeBytes(ROW_END_SUFFIX.getBytes());
        }
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        out.add(bytes);
    }

    private int culLen(String[] command) {
        int length = PREFIX_LENGTH + Integer.BYTES + SUFFIX_LENGTH;

        for (String s : command) {
            length += PREFIX_LENGTH + Integer.BYTES + SUFFIX_LENGTH + s.getBytes().length + SUFFIX_LENGTH;
        }
        return length;
    }
}
