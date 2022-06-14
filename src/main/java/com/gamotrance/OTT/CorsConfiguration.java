package com.gamotrance.OTT;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Configuration
public class CorsConfiguration extends WebMvcConfigurerAdapter {
//The first:
	// @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        /*
// * 1: Access path
// * 2: The source of the request
// * 3: Request method
// * 4: Tocken is allowed
// * 5: Maximum response time
//        *
//        * */
//
//        registry.addMapping("/**")
//                .allowedOrigins("Http:localhost:8080","null")
//                .allowedHeaders("GET","POST","PUT","OPTIONS","DELETE")
//                .allowCredentials(true)
//                .maxAge(3600);
//
//    }
//The second type:
//    @Override
//    public  void  addCorsMappings(CorsRegistry registry){
//        registry.addMapping("/**")
//                .allowedOrigins("http://65.2.89.128:8080")
//                .allowedHeaders("*")
//                .allowedMethods("*")
//                .maxAge(30*1000);
//    }

}