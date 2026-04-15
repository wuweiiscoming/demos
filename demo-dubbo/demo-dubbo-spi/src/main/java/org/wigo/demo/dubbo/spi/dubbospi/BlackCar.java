package org.wigo.demo.dubbo.spi.dubbospi;

import org.apache.dubbo.common.URL;

/**
 * @author wuwei31
 * @since 2021/6/26
 */
public class BlackCar implements Car{

    @Override
    public String getCarName(URL url) {
        System.out.println("black");
        return "black";
    }
}
