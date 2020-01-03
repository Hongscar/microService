package basic.utils.feign;

import basic.controller.TestController;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import feign.Contract;
import feign.Feign;
import feign.Target;
import feign.hystrix.HystrixFeign;
import feign.hystrix.SetterFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import java.lang.reflect.Method;

/**
 * @Author: Seth
 * @Description:
 * @Date: Created in 14:49 2019/12/3
 */

/*      Feign Default Configuration. copy from Spring cloud document.
Spring Cloud Netflix provides the following beans by basic for feign (BeanType beanName: ClassName):

Decoder feignDecoder: ResponseEntityDecoder (which wraps a SpringDecoder)

Encoder feignEncoder: SpringEncoder

Logger feignLogger: Slf4jLogger

Contract feignContract: SpringMvcContract

Feign.Builder feignBuilder: HystrixFeign.Builder

Client feignClient: if Ribbon is in the classpath and is enabled it is a LoadBalancerFeignClient,
 otherwise if Spring Cloud LoadBalancer is in the classpath, FeignBlockingLoadBalancerClient is used.
  If none of them is in the classpath, the basic feign client is used.

 */

// no need to add @Configuration, or it can't under the @ComponentScan class.
// we can add it to the FeignClient Interface by according the @FeignClient.
public class FeignConfiguration {

    @Bean
    public Contract feignContract() {

        return new feign.Contract.Default();
    }

    //@Bean
    //@Scope("prototype")
    //@ConditionalOnMissingBean
    public Feign.Builder feignHystrixBuilder() {
        return HystrixFeign.builder().setterFactory((target, method) -> {
            HystrixCommandProperties.Setter setter = HystrixCommandProperties.Setter();
            setter.withExecutionTimeoutInMilliseconds(4000);
            setter.withFallbackEnabled(true);
            setter.withCircuitBreakerEnabled(true);
            setter.withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD);
            setter.withCircuitBreakerRequestVolumeThreshold(10);
            setter.withCircuitBreakerErrorThresholdPercentage(40);

            HystrixThreadPoolProperties.Setter threadSetter = HystrixThreadPoolProperties.Setter();
            threadSetter.withCoreSize(1);
            threadSetter.withMaxQueueSize(10);
            threadSetter.withKeepAliveTimeMinutes(1000);
            threadSetter.withQueueSizeRejectionThreshold(8);
            threadSetter.withMetricsRollingStatisticalWindowBuckets(12);
            threadSetter.withMetricsRollingStatisticalWindowInMilliseconds(1440);

            return HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(
                    TestController.class.getSimpleName())).andCommandPropertiesDefaults(setter).
                    andThreadPoolPropertiesDefaults(threadSetter);
        });
    }


}
