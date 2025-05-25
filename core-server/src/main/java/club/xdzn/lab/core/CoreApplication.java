package club.xdzn.lab.core;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 核心服务启动类
 * @author shelly
 * @date 2025/05/25
 */
@SpringBootApplication
@ComponentScan("club.xdzn.lab")
public class CoreApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(CoreApplication.class, args);
    }
}
