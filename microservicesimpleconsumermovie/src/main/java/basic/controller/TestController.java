package basic.controller;

import basic.domain.User;
import basic.utils.feign.UserFeignClient;
import feign.Client;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.hystrix.HystrixFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Seth
 * @Description:
 * @Date: Created in 1:13 2019/12/8
 */

@RestController
public class TestController {

    private UserFeignClient userFeignClient;

    @Autowired
    public TestController(Client client, Decoder decoder, UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
//        userFeignClient = HystrixFeign.builder().
//                target(UserFeignClient.class, "http://microservice-simple-provider-user");
    }


    @GetMapping("/user1/{id}")
    public User findById(@PathVariable String id) {
        return this.userFeignClient.findById(id);
    }
}
