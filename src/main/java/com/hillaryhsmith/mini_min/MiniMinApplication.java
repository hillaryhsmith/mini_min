package com.hillaryhsmith.mini_min;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class MiniMinApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniMinApplication.class, args);
	}

}
