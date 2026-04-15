package org.wigo.demo.mydog.client;

import lombok.Data;

/**
 * @author wuwei31
 * @since 2021/6/1
 */
@Data
public class ClientConfig {

    private String host = "localhost";

    private int port = 6379;

}
