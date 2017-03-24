package com.example.www;

import com.example.App;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AbstractRefreshableWebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebInitializer implements WebApplicationInitializer {


    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(App.AppConfiguration.class);

        servletContext.addListener(new ContextLoaderListener(ctx));


        AnnotationConfigWebApplicationContext webCtx = new AnnotationConfigWebApplicationContext();
        webCtx.register(SpringWebMvcConfiguration.class);

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
