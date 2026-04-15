package org.wigo.demo.dubbo.spi.dubbospi;

/**
 * @author wuwei31
 * @since 2021/6/26
 */
public class BlackPerson implements Person{

    private Car car;

    @Override
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
