package basic.utils.feign;

import basic.domain.User;
//import com.netflix.ribbon.proxy.annotation.Hystrix;
import feign.Param;
import feign.RequestLine;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: Seth
 * @Description:
 * @Date: Created in 14:13 2019/12/3
 */

@FeignClient(name = "microservice-simple-provider-user", configuration = FeignConfiguration.class,
    fallback = FeignClientFallback.class, primary = false)
//configuration = {FeignConfiguration.class, FooConfiguration.class}
public interface UserFeignClient {
    /*
            use the default annotation of feign @RequestLine
            @see https://github.com/OpenFeign/feign
            @param id user id
            @return user info
     */
//    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
//    public User findById(@PathVariable("id") String id);

    @RequestLine("GET /{id}")
    //@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User findById(@Param("id") String id);



    //@RequestMapping(value = "/get333", method = RequestMethod.GET)
    @RequestLine("GET /get333")
    public User get2(@RequestParam Map<String, Object> map);

//    @RequestMapping(value = "/get", method = RequestMethod.GET)
//    public User get1(@RequestParam("username") String username, @RequestParam("name") String name);

    //@RequestMapping(value = "/post", method = RequestMethod.POST)
    @RequestLine("POST /post")
    public User post(@RequestBody User user);

//    @RequestMapping(value = "/get", method = RequestMethod.GET)
//    public User get2(@RequestParam Map<String, Object> map);


}


@Component
class FeignClientFallback implements UserFeignClient {

    @Override
    public User findById(String id) {
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

}


