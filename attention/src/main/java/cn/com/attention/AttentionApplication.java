package cn.com.attention;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
//@EnableDiscoveryClient
//@EnableFeignClients
//@EnableAsync
//@ServletComponentScan
//@EnableScheduling
//@EnableTransactionManagement
@Slf4j
public class AttentionApplication {
    @Autowired
    private Environment env;
    public static void main(String[] args) {
        log.info("@slf4j:", args);
        ApplicationContext applicationContext = SpringApplication.run(AttentionApplication.class, args);
        for (String name : applicationContext.getBeanDefinitionNames()) {
            //获得所有bean;
         log.info("扫描出的所有bean:{}",name);
        }
    }

//    @Bean(name = "dataSource")
//    public DataSource dataSource() {
//        String diver = null;
//        ComboPooledDataSource ds = new ComboPooledDataSource();
//        try {
//            diver = env.getProperty("jdbc.driver");
//            ds.setDriverClass(diver);
//            String url = env.getProperty("jdbc.url");
//            String username = env.getProperty("jdbc.username");
//            String password = env.getProperty("jdbc.password");
//            ds.setJdbcUrl(url);
//            ds.setUser(username);
//            ds.setPassword(password);
//            //当连接池用完时客户端调用getConnection()后等待获取新连接的时间  如设为0则无限期等待。单位毫秒，默认为0；
//            ds.setCheckoutTimeout(0);
//            //如果设为true那么在取得连接的同时将校验连接的有效性。默认为false
//            ds.setTestConnectionOnCheckout(true);
//            ds.setIdleConnectionTestPeriod(60);
//            log.info("---------------------->>>>load jdbc url info:{}", url);
//        } catch (PropertyVetoException e) {
//            log.info("---------------------->>>>init datasource error ", e);
//        }
//        return ds;
//    }
//
//    @Bean(name = "jdbcTemplate")
//    @Autowired
//    public JdbcTemplate jdbcTemplate(@Qualifier("dataSource")DataSource dataSourcePostgres) {
//        return new JdbcTemplate(dataSourcePostgres);
//    }
}
