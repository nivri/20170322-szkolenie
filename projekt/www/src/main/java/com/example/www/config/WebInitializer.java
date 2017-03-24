package com.example.www.config;

import com.example.App;
import com.example.www.MyServlet;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebInitializer implements WebApplicationInitializer {


    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(App.AppConfiguration.class);
        ctx.register(WebSecurityConfiguration.class, BasicAuthConfiguration.class);

        servletContext.addListener(new ContextLoaderListener(ctx));

        AnnotationConfigWebApplicationContext webCtx = new AnnotationConfigWebApplicationContext();
        webCtx.register(SpringWebMvcConfiguration.class);


        FilterRegistration.Dynamic springSecurityFilterChain =
                servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy());
        springSecurityFilterChain.addMappingForUrlPatterns(null, false, "/*");


        ServletRegistration.Dynamic dispatcher =
                servletContext.addServlet("dispatcher",
                        new DispatcherServlet(webCtx));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");


        ServletRegistration.Dynamic mySerlvet =
                servletContext.addServlet("myRequestHandler", new MyServlet());
        mySerlvet.addMapping("/servlet");
    }
}
