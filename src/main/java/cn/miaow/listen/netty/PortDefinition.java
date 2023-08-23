package cn.miaow.listen.netty;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 端口配置类
 *
 * @author miaow
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "netty")
public class PortDefinition {

    int[] port;
}
