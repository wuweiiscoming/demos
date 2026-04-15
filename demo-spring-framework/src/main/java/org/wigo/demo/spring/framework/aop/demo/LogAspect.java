package org.wigo.demo.spring.framework.aop.demo;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author wuwei
 * @since 2021/11/28
 */
@Aspect
public class LogAspect {



    @Pointcut
    public void pointCut(){
    }

    @Before("pointCut()")
    public void before(){
        System.out.println();
    }

    public LogAspect(){
        a = 222;
    }

    private int a = 111;

    public static void main(String[] args) {
        LogAspect aspect = new LogAspect();
        System.out.println(aspect.a);
    }

}
