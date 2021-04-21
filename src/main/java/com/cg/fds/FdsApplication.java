package com.cg.fds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.cg.fds.ui.CustomUi;

@SpringBootApplication
public class FdsApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context=SpringApplication.run(FdsApplication.class, args);
		CustomUi ui=context.getBean(CustomUi.class);
		ui.start();
	}

	 @Bean
	    public CorsFilter corsFilter(){
	        UrlBasedCorsConfigurationSource src=new UrlBasedCorsConfigurationSource();
	        CorsConfiguration configuration=new CorsConfiguration();
	        configuration.setAllowCredentials(true);
	        configuration.addAllowedHeader("*");
	        configuration.addAllowedOrigin("http://localhost:3000");
	        configuration.addAllowedMethod("*");
	        src.registerCorsConfiguration("/**",configuration);
	        return new CorsFilter(src);
	    }
}
