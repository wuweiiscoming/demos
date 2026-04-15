package org.wigo.demo.java.agent.jar;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * 类文件转换器
 * 更新类加载时的字节数据
 *
 * @author wuwei31
 * @since 2021/7/6
 */
public class MyTransformer implements ClassFileTransformer {

    /**
     * 带包名的类名（点分隔）
     */
    private String targetClassName;

    /**
     * 全限定名（斜杠分隔）
     */
    private String targetVMClassName;

    /**
     * 目标方法
     */
    private String targetMethodName;

    public MyTransformer(String className, String methodName) {
        this.targetVMClassName = className.replaceAll("\\.", "\\/");
        this.targetMethodName = methodName;
        this.targetClassName = className;
    }

    /**
     * 类加载时会执行该函数
     *
     * @param loader
     * @param className           类的全限定名，如：org/wigo/User
     * @param classBeingRedefined
     * @param protectionDomain
     * @param classfileBuffer     类原始字节码
     * @return 修改后的字节码
     * @throws IllegalClassFormatException
     */
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        //判断类名是否为目标类名

        if (!className.equals(targetVMClassName)) {
            return classfileBuffer;
        }
        System.out.println("TargetClass loaded");
        try {
            ClassPool classPool = ClassPool.getDefault();
            CtClass cls = classPool.get(this.targetClassName);
            CtMethod ctMethod = cls.getDeclaredMethod(this.targetMethodName);
            ctMethod.insertBefore("{ System.out.println(\"start\"); }");
            ctMethod.insertAfter("{ System.out.println(\"end\"); }");
            return cls.toBytecode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classfileBuffer;
    }
}
