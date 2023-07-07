package ru.netology;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class ApplicationInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        var context = new AnnotationConfigWebApplicationContext();
        context.scan("ru.netology");
        context.refresh();

        var dispatcher = new DispatcherServlet(context);
        var registration = servletContext.addServlet("app", dispatcher);
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }
}
