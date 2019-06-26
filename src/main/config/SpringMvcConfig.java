import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * <p>Created by mafx on 2019/5/15.该类用于装配控制层类</p>
 * @author mafx
 * @version 1.0
 */
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = {"com.bluemsun.news.controller"})
public class SpringMvcConfig extends WebMvcConfigurerAdapter {
    /**
     * @param configurer
     * <p>组织前端控制器处理今天资源</p>
     */

    @Override
    public void  configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
        configurer.enable();
    }

}
