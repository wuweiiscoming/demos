package org.wigo.demo.springboot.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wuwei31
 * @since 2021/4/9
 */
@Data
@ConfigurationProperties(prefix = "demo")
public class DemoProperties {

    private String property1;

    private String property2;

}
