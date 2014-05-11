package com.malyshkin.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:dataSource.properties")
public class DataSourceConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private Environment env;

    private static final String URL = "url";
    private static final String DRIVER_CLASS_NAME = "driverClassName";
    private static final String USER_NAME = "userName";

    @Bean
    public DriverManagerDataSource dataSource() {
        return new DriverManagerDataSource() {{
            setUrl(env.getProperty(URL));
            setDriverClassName(env.getProperty(DRIVER_CLASS_NAME));
            setUsername(env.getProperty(USER_NAME));
            setPassword(StringUtils.EMPTY);
        }};
    }
}
