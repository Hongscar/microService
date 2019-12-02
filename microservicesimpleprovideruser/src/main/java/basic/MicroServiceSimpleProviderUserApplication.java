package basic;

import com.netflix.discovery.DiscoveryClient.DiscoveryClientOptionalArgs;
import com.sun.jersey.api.client.filter.ClientFilter;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Seth
 * @Description:
 * @Date: Created in 10:41 2019/11/27
 */
@SpringBootApplication
public class MicroServiceSimpleProviderUserApplication {

    @Bean
    @SuppressWarnings("all")
    public DiscoveryClientOptionalArgs discoveryClientOptionalArgs() {
        DiscoveryClientOptionalArgs discoveryClientOptionalArgs = new DiscoveryClientOptionalArgs();
        List<ClientFilter> additionalFilters = new ArrayList<>();
        additionalFilters.add(new HTTPBasicAuthFilter("hong", "kk123"));
        discoveryClientOptionalArgs.setAdditionalFilters(additionalFilters);
        return discoveryClientOptionalArgs;
    }

    public static void main(String[] args) {
        SpringApplication.run(MicroServiceSimpleProviderUserApplication.class, args);
    }
}
