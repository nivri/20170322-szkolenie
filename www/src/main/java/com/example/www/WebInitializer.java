package com.example.www;

import com.example.App;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebInitializer implements WebApplicationInitializer {


    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(App.AppConfiguration.class);

        servletContext.addListener(new ContextLoaderListener(ctx));

        ServletRegistration.Dynamic dispatcher =
                servletContext.addServlet("myRequestHandler", new MyServlet());
        dispatcher.addMapping("/");
    }
}
