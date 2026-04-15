package org.wigo.demo.mydog.netty.splitpacket;

import lombok.Data;

/**
 * @author wuwei31
 * @since 2021/5/21
 */
@Data
public class PacketWrapper {

    private int length;

    private byte[] content;
}
