//package basic.configure;
//
///**
// * @Author: Seth
// * @Description:
// * @Date: Created in 16:37 2019/12/16
// */
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.util.AntPathMatcher;
//import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
///**
// * @Author: Seth
// * @Description:
// * @Date: Created in 16:22 2019/12/16
// */
//
//@Configuration
//public class SpringWebConfigure extends WebMvcConfigurationSupport {
//
//    @Override
//    public void configurePathMatch(PathMatchConfigurer configurer) {
//        AntPathMatcher pathMatcher = new AntPathMatcher();
//        pathMatcher.setCaseSensitive(false);
//        configurer.setPathMatcher(pathMatcher);
//    }
//
//}