package hu.kleatech.bead1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Bead1Application {
	public static void main(String[] args) {
		SpringApplication.run(Bead1Application.class, args);
	}
}
