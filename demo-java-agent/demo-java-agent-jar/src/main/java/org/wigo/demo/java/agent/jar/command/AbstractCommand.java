package org.wigo.demo.java.agent.jar.command;

import arthas.VmTool;
import arthas.VmToolUtils;
import lombok.Data;

import java.io.File;

/**
 * @author wuwei31
 * @since 2021/7/8
 */
@Data
public abstract class AbstractCommand {

    protected static VmTool vmTool;
    static {
        String libName = VmToolUtils.detectLibName();
        vmTool = VmTool.getInstance("D:\\MyProjects\\demos\\demo-java-agent\\arthas-vmtool\\src\\main\\resources\\lib" + File.separator + libName);
    }

    public abstract String process();
}
