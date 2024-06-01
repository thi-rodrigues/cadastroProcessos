package com.fsrb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CadastroProcessosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastroProcessosApplication.class, args);
	}

}
