import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import redis.clients.jedis.Jedis;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * <p>Created by mafx on 2019/5/15.该类用于装配业务层类</p>
 * @author mafx
 * @version 1.0
 */
@Configuration
@ComponentScan(basePackages ={"com.bluemsun.news.dao","com.bluemsun.news.service"} )
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass = false)
public class ApplicationConfig {
    /**
     * @return datasource
     * <p>使用c3p0数据库连接池配置DataSource</p>
     */
    @Bean
    public DataSource getDataSource(){
        ComboPooledDataSource comboPooledDataSource=new ComboPooledDataSource();
        try {
            comboPooledDataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/news_management");
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
    /**
     * @param dataSource
     * @return JdbcTemplate
     * <p>配置JdbcTemplate持久层框架，用于数据库的操作</p>
     *
     */
    @Bean
    public JdbcTemplate getJdbcTemplate(DataSource dataSource){
        JdbcTemplate jdbcTemplate=new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }
    /**
     * @return DataSourceTransactionManager
    * <p>配置事务管理类</p>
    */
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(){
        return new DataSourceTransactionManager(getDataSource());
    }
    @Bean
    public Jedis getJedis(){

        Jedis jedis=new Jedis("192.168.116.130",6379);
        return jedis;
    }
    @Bean
    public TransportClient getTransportClient() throws UnknownHostException {
        Settings settings=Settings.builder().put("cluster.name","elasticsearch").build();
        TransportClient client=new PreBuiltTransportClient(settings);
        TransportAddress address=new TransportAddress(InetAddress.getByName("192.168.116.130"),9300);
        client.addTransportAddress(address);
        return client;
    }
}
