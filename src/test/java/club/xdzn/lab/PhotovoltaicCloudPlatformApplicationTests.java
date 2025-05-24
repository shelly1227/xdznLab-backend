package club.xdzn.lab;

import club.xdzn.lab.utils.PasswordUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PhotovoltaicCloudPlatformApplicationTests {
    @Test
    void contextLoads() {
        System.out.println(PasswordUtils.encrypt("2019"));
    }

    @Test
    void testPortGenerate() {
        String password = "C1006";
        String year = "2019";
        String team = "XDZN";
        int port = PasswordUtils.generatePort(password, year, team);
        System.out.println("生成端口号为：" + port);
    }

}
