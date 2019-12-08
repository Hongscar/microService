package basic.controller;

import basic.domain.User;
import basic.utils.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Seth
 * @Description:
 * @Date: Created in 1:13 2019/12/8
 */

//@RestController
public class TestController {

    private final UserFeignClient userFeignClient;

    @Autowired
    public TestController(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }


    @GetMapping("/user/{id}")
    public User findById(@PathVariable String id) {
        return this.userFeignClient.findById(id);
    }
}
