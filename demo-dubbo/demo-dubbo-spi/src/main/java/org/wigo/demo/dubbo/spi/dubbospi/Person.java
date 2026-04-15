package org.wigo.demo.dubbo.spi.dubbospi;

import org.apache.dubbo.common.extension.SPI;

/**
 * @author wuwei31
 * @since 2021/6/26
 */
@SPI
public interface Person {

    Car getCar();
}
