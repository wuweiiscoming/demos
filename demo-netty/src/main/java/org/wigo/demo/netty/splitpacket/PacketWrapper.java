package org.wigo.demo.netty.splitpacket;

import lombok.Data;

/**
 * @author wuwei31
 * @since 2021/5/10
 */
@Data
public class PacketWrapper {

    private int length;

    private byte[] content;
}
