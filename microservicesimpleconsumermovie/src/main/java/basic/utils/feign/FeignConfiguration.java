package basic.utils.feign;

import feign.Contract;
import org.springframework.context.annotation.Bean;

/**
 * @Author: Seth
 * @Description:
 * @Date: Created in 14:49 2019/12/3
 */

/*      Feign Default Configuration. copy from Spring cloud document.
Spring Cloud Netflix provides the following beans by default for feign (BeanType beanName: ClassName):

Decoder feignDecoder: ResponseEntityDecoder (which wraps a SpringDecoder)

Encoder feignEncoder: SpringEncoder

Logger feignLogger: Slf4jLogger

Contract feignContract: SpringMvcContract

Feign.Builder feignBuilder: HystrixFeign.Builder

Client feignClient: if Ribbon is in the classpath and is enabled it is a LoadBalancerFeignClient,
 otherwise if Spring Cloud LoadBalancer is in the classpath, FeignBlockingLoadBalancerClient is used.
  If none of them is in the classpath, the default feign client is used.

 */

// no need to add @Configuration, or it can't under the @ComponentScan class.
// we can add it to the FeignClient Interface by according the @FeignClient.
public class FeignConfiguration {

    @Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }
}
