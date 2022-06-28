package br.com.sd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = {"br.com.sd"})
public class BackendSdConectaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendSdConectaApplication.class, args);
	}

}
