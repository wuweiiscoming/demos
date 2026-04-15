package org.wigo.demo.dubbo.spi.dubbospi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

/**
 * @author wuwei31
 * @since 2021/6/26
 */
@SPI
public interface Car {

    @Adaptive
    String getCarName(URL url);
}
