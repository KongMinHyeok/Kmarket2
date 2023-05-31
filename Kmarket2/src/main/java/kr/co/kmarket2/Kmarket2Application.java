package kr.co.kmarket2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Kmarket2Application {

	public static void main(String[] args) {
		SpringApplication.run(Kmarket2Application.class, args);
	}

}
