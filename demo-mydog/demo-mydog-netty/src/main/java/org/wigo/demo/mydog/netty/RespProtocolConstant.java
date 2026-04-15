package org.wigo.demo.mydog.netty;

/**
 * @author wuwei
 * @since 2021/5/20
 */
public class RespProtocolConstant {

    public static final char ARG_NUM_PREFIX = '*';

    public static final char ARG_LEN_PREFIX = '$';

    public static final String ROW_END_SUFFIX = "\r\n";


    public static final int PREFIX_LENGTH = 1;

    public static final int SUFFIX_LENGTH = ROW_END_SUFFIX.getBytes().length;

}
