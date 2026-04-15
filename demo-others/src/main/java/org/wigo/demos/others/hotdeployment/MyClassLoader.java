package org.wigo.demos.others.hotdeployment;

import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class MyClassLoader extends ClassLoader {

    public List<String> classes;

    public MyClassLoader(String... classPaths) {
        classes = new ArrayList<>();

        for (String classPath : classPaths) {
            scan(new File(classPath));
        }
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Class clazz = findLoadedClass(name);
        if (clazz == null) {
            if (!classes.contains(clazz)) {
                clazz = getSystemClassLoader().loadClass(name);
            } else {
                throw new ClassNotFoundException("没找到");
            }
        }
        return clazz;
    }

    @SneakyThrows
    private void scan(File file) {
        if (file.isDirectory()) {
            for (File file1 : file.listFiles()) {
                scan(file1);
            }
        } else {
            byte[] bytes = Files.readAllBytes(file.toPath());
            Class clazz = defineClass(null, bytes, 0, bytes.length);
            classes.add(clazz.getName());
        }
    }

}
