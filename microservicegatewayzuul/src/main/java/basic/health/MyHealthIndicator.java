package basic.health;

/**
 * @Author: Seth
 * @Description:
 * @Date: Created in 15:32 2019/12/17
 */

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component("my3")
public class MyHealthIndicator implements HealthIndicator {
    private  static final String VERSION = "v1.0.0";
    @Override
    public Health health() {
        int code = 0;
        if(code != 0){
            Health.down().withDetail("code",code).withDetail("version",VERSION).build();
        }
        return Health.up().withDetail("code",code).withDetail("version",VERSION).up().build();
    }
}