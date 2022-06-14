package com.gamotrance.OTT;

import static springfox.documentation.builders.PathSelectors.regex;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gamotrance.OTT.Doa.CatogaryRepo;
import com.gamotrance.OTT.Doa.RoleRepository;
import com.gamotrance.OTT.Model.Cataogry;
import com.gamotrance.OTT.Model.PeerType;
import com.gamotrance.OTT.Model.Role;
import com.gamotrance.OTT.Model.SingleVideo;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableConfigurationProperties({
		FileStorageProperties.class
})
@SpringBootApplication
@EnableScheduling
@EnableCaching
public class OttApplication  {

	public static void main(String[] args) {
		SpringApplication.run(OttApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(RoleRepository roleRepository) {

	    return args -> {

	        Role adminRole = roleRepository.findByRole("ADMIN");
	        if (adminRole == null) {
	            Role newAdminRole = new Role();
	            newAdminRole.setRole("ADMIN");
	            roleRepository.save(newAdminRole);
	        }

	        Role userRole = roleRepository.findByRole("USER");
	        if (userRole == null) {
	            Role newUserRole = new Role();
	            newUserRole.setRole("USER");
	            roleRepository.save(newUserRole);
	        }
	    };

	
	}


//	@Bean
//	public Docket productApi() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.select()
//				.apis(RequestHandlerSelectors.basePackage("com.gamotrance.OTT.controller"))
//				.paths(regex("/.*"))
//				.build()
//				.apiInfo(metaData());
//	}
//
//	private ApiInfo metaData() {
//		return new ApiInfoBuilder()
//				.title("OTT APIs")
//				.description("OTT REST API for CrossPlatfrom Developement")
//				.version("1.0.0")
//				.license("Apache License Version 2.0")
//				.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
//				.contact(new Contact("Gamotrance Inc.\n ", "https://www.gamotrance.com/", "dev@gamotrance.com"))
//				.build();
//	}

	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://65.2.89.128:8080");
			}
		};
	}
	
//	@Bean
//	FirebaseMessaging firebaseMessaging() throws IOException {
//
//		 GoogleCredentials googleCredentials = GoogleCredentials
//		            .fromStream(new ClassPathResource("firebase-service-account.json").getInputStream());
//		    FirebaseOptions firebaseOptions = FirebaseOptions
//		            .builder()
//		            .setCredentials(googleCredentials)
//		            .build();
//		    FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, "angur-app");
//
//	    
//	    return FirebaseMessaging.getInstance(app);
//	}
//	
}
