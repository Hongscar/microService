package basic.utils.feign;

/**
 * @Author: Seth
 * @Description:
 * @Date: Created in 18:10 2019/12/4
 */

import feign.codec.Encoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignSimpleEncoderConfig {
    @Bean
    public Encoder encoder(){
        return new FormEncoder();
    }
}