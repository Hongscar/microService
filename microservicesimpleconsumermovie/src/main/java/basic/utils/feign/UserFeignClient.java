package basic.utils.feign;

import basic.domain.User;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: Seth
 * @Description:
 * @Date: Created in 14:13 2019/12/3
 */

//@FeignClient(name = "microservice-simple-provider-user",
//        configuration = {FeignConfiguration.class, FooConfiguration.class})
public interface UserFeignClient {
    /*
            use the default annotation of feign @RequestLine
            @see https://github.com/OpenFeign/feign
            @param id user id
            @return user info
     */
    @RequestLine("GET /{id}")
    public User findById(@Param("id") String id);
}
