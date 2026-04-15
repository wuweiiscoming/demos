package org.wigo.demo.java.agent.jar.ognl;

import lombok.SneakyThrows;
import ognl.Ognl;
import ognl.OgnlContext;

/**
 * @author wuwei31
 * @since 2021/7/7
 */
public class OgnlHandler {

    private OgnlContext context = new OgnlContext();

    public String handle(Object instances, String express) {
        context.put("instances", instances);
        return handle(express);
    }

    @SneakyThrows
    public String handle(String express) {
        Object ognl = Ognl.parseExpression(express);

        // 解析表达式
        Object value = Ognl.getValue(ognl, context, context.getRoot());

        return value.toString();
    }
}
