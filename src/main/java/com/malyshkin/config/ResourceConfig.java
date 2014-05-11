package com.malyshkin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@PropertySource("classpath:commonConfig.properties")
public class ResourceConfig extends WebMvcConfigurerAdapter {

    @Autowired
    Environment env;

    private static final String PREFIX = "prefix";
    private static final String SUFFIX = "suffix";

    private static final String HANDLER_TYPE = "resource.handler.type";
    private static final String RESOURCE_LOCATIONS = "resource.locations";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(env.getProperty(HANDLER_TYPE)).addResourceLocations(env.getProperty(RESOURCE_LOCATIONS));
    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        return new InternalResourceViewResolver() {{
            setPrefix(env.getProperty(PREFIX));
            setSuffix(env.getProperty(SUFFIX));
        }};
    }
}
