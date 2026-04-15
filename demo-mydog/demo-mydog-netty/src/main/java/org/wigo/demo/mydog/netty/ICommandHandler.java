package org.wigo.demo.mydog.netty;

/**
 * @author wuwei
 * @since 2021/5/20
 */
public interface ICommandHandler {

    String handle(String[] command);

}
