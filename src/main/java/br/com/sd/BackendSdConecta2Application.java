package br.com.sd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BackendSdConecta2Application {

	public static void main(String[] args) {
		SpringApplication.run(BackendSdConecta2Application.class, args);
	}

}
