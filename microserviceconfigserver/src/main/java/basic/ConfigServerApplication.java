package basic;

import com.netflix.discovery.DiscoveryClient;
import com.sun.jersey.api.client.filter.ClientFilter;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Seth
 * @Description:
 * @Date: Created in 10:16 2019/12/30
 */
@SpringBootApplication
@EnableConfigServer
//@EnableEurekaClient
public class ConfigServerApplication {

    @Bean
    public DiscoveryClient.DiscoveryClientOptionalArgs discoveryClientOptionalArgs() {
        DiscoveryClient.DiscoveryClientOptionalArgs discoveryClientOptionalArgs = new DiscoveryClient.DiscoveryClientOptionalArgs();
        List<ClientFilter> additionalFilters = new ArrayList<>();
        additionalFilters.add(new HTTPBasicAuthFilter("hong", "root"));
        discoveryClientOptionalArgs.setAdditionalFilters(additionalFilters);
        System.out.println("Config server");
        return discoveryClientOptionalArgs;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
