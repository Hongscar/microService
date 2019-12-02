package basic.controller;

import basic.domain.User;
//import com.netflix.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Author: Seth
 * @Description:
 * @Date: Created in 11:02 2019/11/27
 */

@RestController
public class MovieController {
    private final RestTemplate restTemplate;

    private final DiscoveryClient discoveryClient;

    @Autowired
    public MovieController(RestTemplate restTemplate, DiscoveryClient discoveryClient) {
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
    }

//    @Autowired
//    public MovieController(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Integer id) {
        return restTemplate.getForObject("http://localhost:8000/" + id, User.class);
    }


    public List<ServiceInstance> showInfo() {
        return discoveryClient.getInstances("microservice-provider-user");
    }

}
