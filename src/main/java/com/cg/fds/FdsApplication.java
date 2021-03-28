package com.cg.fds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.cg.fds.ui.CustomUi;

@SpringBootApplication
public class FdsApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context=SpringApplication.run(FdsApplication.class, args);
		CustomUi ui=context.getBean(CustomUi.class);
		ui.start();
	}

}
