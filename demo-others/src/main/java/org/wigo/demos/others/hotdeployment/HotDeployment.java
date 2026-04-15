package org.wigo.demos.others.hotdeployment;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

public class HotDeployment {

    public static void main(String[] args) throws Exception {
        run();
    }

    private static void run() throws Exception {
        String classPath = "E:\\workspace\\demos\\target\\classes\\cn\\wuwei\\demos\\hotdeployment";
        MyClassLoader myClassLoader = new MyClassLoader(classPath);
        start0(myClassLoader);
        startFileListener(classPath);
    }

    public static void start() {
        new TestClass();
    }

    /**
     * 使用自定义类加载器加载启动类，并调用start方法，实现全盘委托
     *
     * @param myClassLoader
     * @throws Exception
     */
    public static void start0(MyClassLoader myClassLoader) throws Exception {
        Class clazz = myClassLoader.loadClass("org.wigo.demos.others.hotdeployment.HotDeployment");
        clazz.getDeclaredMethod("start").invoke(null);
    }

    /**
     * 启动文件监听器，监听class文件变化
     * @param classPath
     * @throws Exception
     */
    public static void startFileListener(String classPath) throws Exception {
        FileAlterationObserver observer = new FileAlterationObserver(classPath);
        observer.addListener(new FileListener(classPath));
        FileAlterationMonitor monitor = new FileAlterationMonitor(500);
        monitor.addObserver(observer);
        monitor.start();
    }
}
