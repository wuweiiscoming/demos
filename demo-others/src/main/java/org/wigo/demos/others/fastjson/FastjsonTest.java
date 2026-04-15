package org.wigo.demos.others.fastjson;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * Fastjson中
 * 实体类的属性如果是接口类型，那么在序列化时，会丢失实现类自有的属性
 * 需要使用SerializerFeature.WriteClassName，在序列化时保存其实现类名
 */
@Data
public class FastjsonTest {

    private String name;

    private Fruit fruit;

    interface Fruit {
    }

    @Data
    static class Apple implements Fruit {
        private double price;
    }

    public static void main(String[] args) {
        Apple apple = new Apple();
        apple.setPrice(100.1);
        FastjsonTest test = new FastjsonTest();
        test.setFruit(apple);
        test.setName("呵呵");
//        String jsStr = JSONObject.toJSONString(test, SerializerFeature.WriteClassName);
        String jsStr = JSONObject.toJSONString(test);
        System.out.println(jsStr);
        FastjsonTest result = JSONObject.parseObject(jsStr, FastjsonTest.class);
        System.out.println(JSONObject.toJSONString(result));
    }

}
