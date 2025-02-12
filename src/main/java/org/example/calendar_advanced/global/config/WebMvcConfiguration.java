package org.example.calendar_advanced.global.config;

import org.example.calendar_advanced.global.filter.AuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean authFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new AuthFilter());
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("/api/login");
        registrationBean.addUrlPatterns("/api/users/*");
        registrationBean.addUrlPatterns("/api/schedules/*");
        registrationBean.addUrlPatterns("/api/comments/*");

        return registrationBean;
    }

}
