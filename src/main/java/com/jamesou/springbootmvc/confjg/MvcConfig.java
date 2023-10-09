package com.jamesou.springbootmvc.confjg;

import com.jamesou.springbootmvc.Interceptor.LoginInterceptor;
import com.jamesou.springbootmvc.LocalResolver.MyLocalResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author jamesou
 * @create 2020-04-12 11:47
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/success").setViewName("success");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/asserts/css/**")
                .addResourceLocations("classpath:/static/asserts/css/");
        registry
                .addResourceHandler("/asserts/img/**")
                .addResourceLocations("classpath:/static/asserts/img/");
        registry
                .addResourceHandler("/asserts/js/**")
                .addResourceLocations("classpath:/static/asserts/js/");        
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
        .addPathPatterns("/**")
        .excludePathPatterns("/","/register","/user/signup","/signup","/user/login")
        .excludePathPatterns("/asserts/css/**", "/asserts/img/**", "/asserts/js/**","/webjars/**");
    }
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocalResolver();
    }
    
}
