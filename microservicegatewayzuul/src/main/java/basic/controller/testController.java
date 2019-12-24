package basic.controller;

import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: Seth
 * @Description:
 * @Date: Created in 19:21 2019/12/24
 */
@RestController
public class testController {
    public final RestTemplate restTemplate;

    @Autowired
    public testController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/test/{string}")
    public String findById(@PathVariable String string) {
        return restTemplate.getForObject("http://localhost:8060/" + string, String.class);
    }

    @GetMapping("/test11/{id}")
    public User test(@PathVariable String id) {
        return restTemplate.getForObject("http://localhost:8001/" + id, User.class);
    }
}
