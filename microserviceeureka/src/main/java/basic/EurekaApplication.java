package basic;

import basic.configuration.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Import;

/**
 * @Author: Seth
 * @Description:
 * @Date: Created in 10:17 2019/11/28
 */
@SpringBootApplication
@EnableEurekaServer
@Import(WebSecurityConfig.class)
public class EurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }
}
