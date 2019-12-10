package basic.controller;

import basic.domain.User;
//import com.netflix.discovery.DiscoveryClient;
//import basic.utils.feign.FeignConfiguration;
import basic.utils.feign.FormEncoder;
import basic.utils.feign.UserFeignClient;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
//import feign.hystrix.HystrixFeign;
import feign.hystrix.HystrixFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
//import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Seth
 * @Description:
 * @Date: Created in 11:02 2019/11/27
 */

// PS:在构造方法里构建了多个FeignClient(而且是同一个

@Import({FeignClientsConfiguration.class})   // 使用Spring Cloud为Feign的默认配置类就好
@RestController
public class MovieController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

//    private final RestTemplate restTemplate;

    private DiscoveryClient discoveryClient;

//    private LoadBalancerClient loadBalancerClient;


    private UserFeignClient userUserFeignClient;

    private UserFeignClient adminUserFeignClient;

    private FormEncoder encoder;


    @Autowired
    public Encoder getEncoder() {
        return new FormEncoder();
    }

//    @Autowired
//    private UserFeignClient userFeignClient;

//    @Bean
//    public UserFeignClient userFeignClient() {
//        return new UserFeignClient();
//    }

//    @Bean
//    @LoadBalanced
//    public RestTemplate getRestTemplate() {
//        return new RestTemplate();
//    }

    @Autowired
    public MovieController(DiscoveryClient discoveryClient,
                           Client client, Decoder decoder) {
        this.discoveryClient = discoveryClient;
//        this.loadBalancerClient = loadBalancerClient;
//        userUserFeignClient = HystrixFeign.builder().encoder(getEncoder()).decoder(decoder).requestInterceptor(
//                new BasicAuthRequestInterceptor("user", "password1")).
//        target(UserFeignClient.class, "http://microservice-simple-provider-user");
//
//        adminUserFeignClient = HystrixFeign.builder().encoder(getEncoder()).decoder(decoder).requestInterceptor(
//                new BasicAuthRequestInterceptor("admin", "kk123")
//        ).target(UserFeignClient.class, "http://microservice-simple-provider-user");

        // this.restTemplate = restTemplate;
        // this.userFeignClient = userFeignClient;
//        userUserFeignClient = Feign.builder().decoder(decoder).target(BasicAuthRequestInterceptor.class ,
//                "http://microservice-simple-provider-user");

        userUserFeignClient = HystrixFeign.builder().client(client).decoder(decoder).
                requestInterceptor(new BasicAuthRequestInterceptor("user",
                "password1")).target(UserFeignClient.class,
                "http://microservice-simple-provider-user");
        adminUserFeignClient = HystrixFeign.builder().client(client).decoder(decoder).
                requestInterceptor(new BasicAuthRequestInterceptor("admin",
                "kk123")).target(UserFeignClient.class, "http://microservice-simple-provider-user");
    }

    @GetMapping("/user-user/{id}")
    public User findByIdUser(@PathVariable String id) {
        if ("favicon.ico".equals(id))
            return null;
        return userUserFeignClient.findById(id);
    }

    @GetMapping("/admin-user/{id}")
    public User findByIdAdmin(@PathVariable String id) {
        if ("favicon.ico".equals(id))
            return null;
        return adminUserFeignClient.findById(id);
    }

    /**
     * 测试URL:http://localhost:8010/user/post?id=1&username=张三
     * @param  user user
     * @return 用户信息
     */
    @GetMapping("/user/post")
    public User post(User user) {
        return this.userUserFeignClient.post(user);
    }


    /**
     * 测试URL：http://localhost:8010/user/get2?name=kai&username=account3
     * @param   user user
     * @return 用户信息
     */
    @GetMapping("/user/get2")
    public User get2(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", user.getName());
        map.put("username", user.getUsername());
        return this.userUserFeignClient.get2(map);
    }

    //    @Autowired
//    public MovieController(RestTemplate restTemplate, DiscoveryClient discoveryClient) {
//        this.restTemplate = restTemplate;
//        this.discoveryClient = discoveryClient;
//    }

//    @Autowired
//    public MovieController(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }

//    @GetMapping("/user/{id}")
//    public User findById(@PathVariable Integer id) {
//        return restTemplate.getForObject("http://microservice-simple-provider-user/" + id, User.class);
//    }

//    @HystrixCommand(fallbackMethod = "findByIdFallback")
    @GetMapping("/user/{id}")
    public User findById(@PathVariable String id) {
        if ("favicon.ico".equals(id))
            return null;
        return userUserFeignClient.findById(id);
    }

    public User findByIdFallback(String id) {
        User user = new User();
        user.setId("-1");
        user.setName("默认用户");
        user.setAge(66);
        user.setBalance(999);
        user.setUsername("default");
        return user;
    }

//    @RequestMapping(value = "/user/get1", method = RequestMethod.GET)
//    public User get1(User user) {
//        return userUserFeignClient.get1(user.getUsername(), user.getName());
//    }

//    @RequestMapping("/get/{username}/{name}")
//    public User get(@PathVariable String username, @PathVariable String name) {
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("username", username);
//        map.put("name", name);
//        return userFeignClient.get2(map);
//    }

//    @RequestMapping(value = "/user/get2", method = RequestMethod.GET)
//    public User get2(User user) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("id", user.getId());
//        map.put("username", user.getUsername());
//        return this.userFeignClient.get2(map);
//    }

    @GetMapping("/user-instance")
    public List<ServiceInstance> showInfo() {
        return discoveryClient.getInstances("microservice-simple-provider-user");
    }

//    @GetMapping("/log-user-instance")
//    public void logUserInstance() {
//        ServiceInstance serviceInstance = loadBalancerClient.choose("microservice-simple-provider-user");
//        MovieController.LOGGER.info("{}:{}:{}", serviceInstance.getServiceId(),
//                serviceInstance.getHost(), serviceInstance.getPort());
//    }

}
