package org.example.calendar_advanced.global.config;

import org.example.calendar_advanced.global.filter.ScheduleFilter;
import org.example.calendar_advanced.global.filter.UserFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean userFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new UserFilter());
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("/api/users/*");

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean scheduleFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new ScheduleFilter());
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("/api/schedules/*");

        return registrationBean;
    }
}
