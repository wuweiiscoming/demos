package org.wigo.demo.dubbo.spi.dubbospi;

import org.apache.dubbo.common.URL;

/**
 * @author wuwei31
 * @since 2021/6/26
 */
public class CarWrapper implements Car{

    private Car car;

    public CarWrapper(Car car) {
        this.car = car;
    }

    @Override
    public String getCarName(URL url) {
        System.out.println("wrapper");
        car.getCarName(url);
        return null;
    }
}
