package com.hanxinbank.p2p;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.hanxinbank.p2p.web.filter.UserAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.filter.DelegatingFilterProxy;

import java.util.Properties;

@ComponentScan
@EnableAutoConfiguration
@Import(com.hanxinbank.p2p.core.Application.class)
public class Application extends SpringBootServletInitializer {
    @Autowired
    private UserAuthenticationFilter userAuthenticationFilter;

    @Bean
    public Producer captchaProducer() {
        DefaultKaptcha captchaProducer = new DefaultKaptcha();
        captchaProducer.setConfig(new Config(new Properties()));
        return captchaProducer;
    }

    @Bean
    public FilterRegistrationBean openEntityManagerInViewFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new OpenEntityManagerInViewFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("openEntityManagerInViewFilter");
        filterRegistrationBean.setOrder(1);

        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean userAuthenticationFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new DelegatingFilterProxy(userAuthenticationFilter));
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("userAuthenticationFilter");
        filterRegistrationBean.setOrder(2);

        return filterRegistrationBean;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        new Application().configure(new SpringApplicationBuilder(Application.class)).run(
                args);
    }

}
