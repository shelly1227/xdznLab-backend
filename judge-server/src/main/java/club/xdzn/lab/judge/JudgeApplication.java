package club.xdzn.lab.judge;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 判题服务启动类
 * @author shelly
 * @date 2025/05/25
 */
@SpringBootApplication
@ComponentScan("club.xdzn.lab")
public class JudgeApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(JudgeApplication.class, args);
    }
}
