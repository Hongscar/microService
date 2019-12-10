package basic.utils.feign.fallback;

import basic.domain.User;
import basic.utils.feign.UserFeignClient;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: Seth
 * @Description:
 * @Date: Created in 10:27 2019/12/8
 */
@Component
//@Primary
public class FeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {
    private static final Logger LOGGER = LoggerFactory.getLogger(FeignClientFallbackFactory.class);

    @Override
    public UserFeignClient create(Throwable throwable) {
        return new UserFeignClient() {

            @Override
            public User findById(String id) {
                FeignClientFallbackFactory.LOGGER.info("fallback; reason was: ", throwable.toString());
                System.out.println("------");
                throwable.printStackTrace();
                System.out.println("------");
                User user = new User();
                user.setId("-122");
                user.setName("默认用户");
                user.setAge(66);
                user.setBalance(999);
                user.setUsername("default");
                return user;
            }

            @Override
            public User get2(Map<String, Object> map) {
                User user = new User();
                user.setId("-11");
                user.setName("默认用户");
                user.setAge(66);
                user.setBalance(999);
                user.setUsername("default");
                return user;
            }

            @Override
            public User post(User user) {
                user.setId("-1");
                user.setName("默认用户");
                user.setAge(66);
                user.setBalance(999);
                user.setUsername("default");
                return user;
            }
        };
    }
}
