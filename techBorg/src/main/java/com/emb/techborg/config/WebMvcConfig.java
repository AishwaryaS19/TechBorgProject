package com.emb.techborg.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/accessdenied").setViewName("accessdenied");
        registry.addViewController("/").setViewName("homepage");
        registry.addViewController("/aboutus").setViewName("aboutus");
    }
}