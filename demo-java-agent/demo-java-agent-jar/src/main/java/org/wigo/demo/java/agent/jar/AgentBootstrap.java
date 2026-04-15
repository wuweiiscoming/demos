package org.wigo.demo.java.agent.jar;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wigo.demo.java.agent.jar.command.CommandServer;

import java.lang.instrument.Instrumentation;
import java.util.LinkedList;
import java.util.List;

/**
 * 代理jar包被VirtualMachine.attach注入到指定进程后，执行引导逻辑
 *
 * @author wuwei31
 * @since 2021/7/6
 */
public class AgentBootstrap {

    private static Logger logger = LoggerFactory.getLogger(AgentBootstrap.class);

    private static String className = "org.wigo.demo.java.agent.target.TargetClass";
    private static String methodName = "method";

    /**
     * jvm进程被注入代理jar后，将执行此方法
     * 1.如果有需要，可以添加字节码转换器，对指定类的字节码进行修改，实现各种代理逻辑
     * 2.启动ognl服务器，然后客户端就可以通过ognl语言与服务端进行通信
     *
     * @param args
     * @param instrumentation
     */
    public static void agentmain(String args, Instrumentation instrumentation) {
        // 添加字节码转换器
//         addTransformers(instrumentation);
        // 启动ognl服务器
        new Thread(() -> {
            CommandServer ognlServer = new CommandServer();
            ognlServer.start(8888);
        }).start();
    }

    @SneakyThrows
    private static void addTransformers(Instrumentation instrumentation) {
        List<Class> needRetransFormClasses = new LinkedList<>();
        Class[] loadedClass = instrumentation.getAllLoadedClasses();
        for (Class clazz : loadedClass) {
            if (needRetransform(clazz)) {
                needRetransFormClasses.add(clazz);
            }
        }

        instrumentation.addTransformer(new MyTransformer(className, methodName), true);
        logger.info("add ClassFileTransformer:" + MyTransformer.class.getName());

        System.out.println("needRetransFormClasses:" + JSON.toJSONString(needRetransFormClasses));
        instrumentation.retransformClasses(needRetransFormClasses.toArray(new Class[0]));
    }

    private static boolean needRetransform(Class clazz) {
        // 设置类是否需要
        return true;
    }

}
