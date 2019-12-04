package basic.controller;

import basic.domain.User;
//import com.netflix.discovery.DiscoveryClient;
import basic.utils.feign.FeignConfiguration;
import basic.utils.feign.FormEncoder;
import basic.utils.feign.UserFeignClient;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
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

@Import(FeignClientsConfiguration.class)   // 使用Spring Cloud为Fiegn的默认配置类就好
@RestController
public class MovieController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

//    private final RestTemplate restTemplate;

    private DiscoveryClient discoveryClient;

    private LoadBalancerClient loadBalancerClient;

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
    public MovieController(DiscoveryClient discoveryClient, LoadBalancerClient loadBalancerClient,
                           Client client, Decoder decoder) {
        this.discoveryClient = discoveryClient;
        this.loadBalancerClient = loadBalancerClient;
        // this.restTemplate = restTemplate;
        // this.userFeignClient = userFeignClient;
        userUserFeignClient = Feign.builder().client(client).encoder(getEncoder()).decoder(decoder).requestInterceptor(
                new BasicAuthRequestInterceptor("user",
                "password1")).target(UserFeignClient.class,
                "http://microservice-simple-provider-user");
        adminUserFeignClient = Feign.builder().client(client).encoder(getEncoder()).
                decoder(decoder).requestInterceptor(new BasicAuthRequestInterceptor("admin",
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

//    @GetMapping("/user/{id}")
//    public User findById(@PathVariable Long id) {
//        return userFeignClient.findById(id);
//    }

    @GetMapping("/user-instance")
    public List<ServiceInstance> showInfo() {
        return discoveryClient.getInstances("microservice-simple-provider-user");
    }

    @GetMapping("/log-user-instance")
    public void logUserInstance() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("microservice-simple-provider-user");
        MovieController.LOGGER.info("{}:{}:{}", serviceInstance.getServiceId(),
                serviceInstance.getHost(), serviceInstance.getPort());
    }

}
