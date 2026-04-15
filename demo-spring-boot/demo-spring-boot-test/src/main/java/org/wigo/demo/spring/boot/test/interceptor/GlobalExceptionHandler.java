package org.wigo.demo.spring.boot.test.interceptor;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author wuwei31
 * @since 2021/5/19
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value =Exception.class)
    public void handle(Exception e){
        System.out.println("ExceptionHandler");
    }

}
