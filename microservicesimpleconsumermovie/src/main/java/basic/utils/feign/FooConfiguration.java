package basic.utils.feign;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * @Author: Seth
 * @Description:
 * @Date: Created in 15:02 2019/12/3
 */

// when we need to add Interceptor for Feign, we need it to add the Http Basic auth.
public class FooConfiguration {

    //@Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("user", "password");
    }
}
