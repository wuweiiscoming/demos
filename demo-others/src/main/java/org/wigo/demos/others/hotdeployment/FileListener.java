package org.wigo.demos.others.hotdeployment;

import lombok.SneakyThrows;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;

import java.io.File;

/**
 * 文件监听器，监听class文件变化
 */
public class FileListener extends FileAlterationListenerAdaptor {

    private String rootPath;

    public FileListener(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public void onFileChange(File file) {
        System.out.println("文件修改了");
    }

    /**
     * 类编译后，会直接删除原来的class，创建新的class文件，所以在文件创建事件上使用新的类加载器
     *
     * @param file
     */
    @SneakyThrows
    @Override
    public void onFileCreate(File file) {
        System.out.println("文件创建了");
        MyClassLoader myClassLoader = new MyClassLoader(rootPath);
        HotDeployment.start0(myClassLoader);
    }
}
