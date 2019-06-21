import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * Created by mafx on 2019/5/15.
 */
@Configuration
@ComponentScan(basePackages ={"com.bluemsun.news.dao","com.bluemsun.news.service"} )
@EnableTransactionManagement
public class ApplicationConfig {
    @Bean
    public DataSource getDataSource(){
        ComboPooledDataSource comboPooledDataSource=new ComboPooledDataSource();
        try {
            comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/news_management");
            comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
            comboPooledDataSource.setMinPoolSize(5);
            comboPooledDataSource.setAcquireIncrement(5);
            comboPooledDataSource.setInitialPoolSize(2);
            comboPooledDataSource.setAcquireRetryDelay(400);
            comboPooledDataSource.setPreferredTestQuery("select 1");
            comboPooledDataSource.setTestConnectionOnCheckout(true);
            comboPooledDataSource.setTestConnectionOnCheckin(true);
            comboPooledDataSource.setUser("root");
            comboPooledDataSource.setPassword("root");

        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return  comboPooledDataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(DataSource dataSource){
        JdbcTemplate jdbcTemplate=new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }
    /**
    * 配置事务管理类
    */
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(){
        return new DataSourceTransactionManager(getDataSource());
    }
}
