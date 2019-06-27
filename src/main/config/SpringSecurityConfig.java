import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * <p>Created by mafx on 2019/5/15.</p>
 * <p>引入spring-security用于实现安全控制</p>
 * @author mafx
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
@ImportResource("classpath:spring-security.xml")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

}
