package org.wigo.demo.tomcat.servlet;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

/**
 * @author wuwei31
 * @since 2021/3/23
 */
public class Start {

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();

        tomcat.addContext("/xx","E:\\AI模型相关文档\\历史资料");

        tomcat.addServlet("/xx","HelloServlet",new HelloServlet());

        tomcat.setPort(8080);
        tomcat.start();
    }

}
