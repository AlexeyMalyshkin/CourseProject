package com.malyshkin.config.initializer;

import com.malyshkin.config.CommonConfig;
import com.malyshkin.config.DataSourceConfig;
import com.malyshkin.config.EncodingFilter;
import com.malyshkin.config.ResourceConfig;
import com.malyshkin.config.SecurityConfig;
import com.malyshkin.config.SpringDataConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class Initializer implements WebApplicationInitializer {

    private static final String SPRING_SECURITY_FILTER_CHAIN = "springSecurityFilterChain";
    private static final String FILTER_MAPPING = "/*";

    private static final String DISPATCHER_SERVLET = "DispatcherServlet";
    private static final int LOAD_ORDER = 1;
    private static final String DISPATCHER_MAPPING = "/";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx =
                new AnnotationConfigWebApplicationContext();

        registerConfigurations(ctx);

        servletContext.addListener(new ContextLoaderListener(ctx));

        servletContext.addFilter(SPRING_SECURITY_FILTER_CHAIN,
                new DelegatingFilterProxy(SPRING_SECURITY_FILTER_CHAIN))
                .addMappingForUrlPatterns(null, false, FILTER_MAPPING);

        servletContext.addFilter("FormEncodingSetterFilter",
                new EncodingFilter()).addMappingForUrlPatterns(null,false, FILTER_MAPPING);

        ctx.setServletContext(servletContext);

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(DISPATCHER_SERVLET, new DispatcherServlet(ctx));
        dispatcher.setLoadOnStartup(LOAD_ORDER);
        dispatcher.addMapping(DISPATCHER_MAPPING);
    }

    private void registerConfigurations(AnnotationConfigWebApplicationContext ctx) {
        ctx.register(CommonConfig.class);
        ctx.register(SecurityConfig.class);
        ctx.register(DataSourceConfig.class);
        ctx.register(SpringDataConfig.class);
        ctx.register(ResourceConfig.class);
    }
}

