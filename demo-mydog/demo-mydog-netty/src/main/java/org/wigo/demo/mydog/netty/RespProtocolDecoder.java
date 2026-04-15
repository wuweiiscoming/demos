package org.wigo.demo.mydog.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

import static org.wigo.demo.mydog.netty.RespProtocolConstant.*;

/**
 * @author wuwei31
 * @since 2021/5/20
 */
public class RespProtocolDecoder extends MessageToMessageDecoder<byte[]> {

    @Override
    protected void decode(ChannelHandlerContext ctx, byte[] msg, List<Object> out) throws Exception {

        ByteBufAllocator alloc = UnpooledByteBufAllocator.DEFAULT;

        ByteBuf byteBuf = alloc.heapBuffer(msg.length);
        byteBuf.writeBytes(msg);

        char argNumPrefix = byteBuf.readChar();

        if (ARG_NUM_PREFIX != argNumPrefix) {
            throw new RuntimeException();
        }
        int argNum = byteBuf.readInt();

        nextLine(byteBuf);

        String[] args = new String[argNum];
        for (int i = 0; i < argNum; i++) {
            char argLenPrefix = byteBuf.readChar();
            if (ARG_LEN_PREFIX != argLenPrefix) {
                throw new RuntimeException();
            }
            int argLen = byteBuf.readInt();
            nextLine(byteBuf);

            byte[] bytes = new byte[argLen];
            byteBuf.readBytes(bytes);
            nextLine(byteBuf);

            args[i] = new String(bytes);
        }

        out.add(args);
    }

    private void nextLine(ByteBuf byteBuf) {
        byteBuf.readBytes(ROW_END_SUFFIX.getBytes().length);
    }

}
