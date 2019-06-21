import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by mafx on 2019/5/15.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.bluemsun.news.controller"})
public class SpringMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void  configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
        configurer.enable();
    }

}
