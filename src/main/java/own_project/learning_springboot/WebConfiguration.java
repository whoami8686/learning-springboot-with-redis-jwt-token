package own_project.learning_springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import own_project.learning_springboot.resolver.UserArgumentResolver;

import java.util.List;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
//setiap controller yang butuh data user, sistem akan menggunakan resolver
    @Autowired
    private UserArgumentResolver userArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
        resolvers.add(userArgumentResolver);
    }

}
