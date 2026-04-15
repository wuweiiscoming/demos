package org.wigo.demo.java.agent.jar.ognl;

import ognl.*;
import org.junit.Test;

import java.util.Collections;

/**
 * @author wuwei31
 * @since 2021/7/6
 */
public class OgnlDemo {

    //非根元素
    @Test
    public void testOgnl1() throws OgnlException {
        //创建一个Ognl上下文对象
        OgnlContext context = new OgnlContext();

        /**
         * 1.OgnlContext放入基本变量数据
         */
        //放入数据
        context.put("cn","China");
        //获取数据（map）
        String value = (String)context.get("cn");

        System.out.println(value);


        /**
         * 2.OgnlContext放入对象数据
         */
        //创建对象，设置对象属性
        User user = new User();
        user.setId(100);
        user.setName("Jack");
        //【往非根元素放入数据，取值的时候表达式要用“#”】
        context.put("user",user);
        //获取对象属性
        //使用这种方式也可以获取
        Object s = context.get("user");
        System.out.println(s);

        //使用Ognl表达式来获取
        //举例：例如标签<s:a value="#user.id">取值，实际上就是运行了下面的代码获取的
        //先构建一个Ognl表达式，再解析表达式
        Object ognl = Ognl.parseExpression("#user.id");//构建Ognl表达式
        Object value1 = Ognl.getValue(ognl, context, context.getRoot());//解析表达式
        System.out.println(value1);

        User user1 = new User();
        user1.setId(100);
        user1.setName("Jack");
        context.setRoot(user1);
        Object ognl1 = Ognl.parseExpression("id");//构建Ognl表达式
        Object value2 = Ognl.getValue(ognl1, context, context.getRoot());//解析表达式
        System.out.println(value2);

    }


    //根元素，
    @Test
    public void testOgnl2() throws OgnlException {
        OgnlContext context = new OgnlContext();

        User user1 = new User();
        user1.setId(100);
        user1.setName("Jack");
        context.setRoot(user1);
        //根元素直接使用id,不需要加#号
        Object ognl1 = Ognl.parseExpression("id");//构建Ognl表达式
        Object value2 = Ognl.getValue(ognl1, context, context.getRoot());//解析表达式
        System.out.println(value2);

    }

    //ognl对静态方法调用的支持
    @Test
    public void testOgnl3() throws Exception{
        //创建一个Ognl上下文对象
        OgnlContext context = new OgnlContext();

        //Ognl表达式语言，调用类的静态方法
//        Object ognl = Ognl.parseExpression("@Math@floor(10.9)");
        //由于Math类在开发中比较常用，所有也可以这样写
        Object ognl = Ognl.parseExpression("@@floor(10.9)");
        Object value = Ognl.getValue(ognl, context, context.getRoot());
        System.out.println(value);
    }

    @Test
    public void testOgnl4() throws Exception{
        //创建一个Ognl上下文对象
        OgnlContext context = new OgnlContext();

        context.put("user",new User(1,"xx"));
        context.put("user", Collections.singletonList(new User(2,"yy")));
        //Ognl表达式语言，调用类的静态方法
//        Object ognl = Ognl.parseExpression("@Math@floor(10.9)");
        //由于Math类在开发中比较常用，所有也可以这样写
        Object ognl = Ognl.parseExpression("#user[0].id");
        Object value = Ognl.getValue(ognl, context, context.getRoot());
        System.out.println(value);
    }

}
