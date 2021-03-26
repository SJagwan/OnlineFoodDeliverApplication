package com.cg.fds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.cg.fds.ui.OrderUi;

@SpringBootApplication
public class FdsApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context=SpringApplication.run(FdsApplication.class, args);
		OrderUi ui=context.getBean(OrderUi.class);
		ui.start();
	}

}
