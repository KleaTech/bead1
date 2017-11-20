package hu.kleatech.bead1;

import hu.kleatech.bead1.model.Color;
import hu.kleatech.bead1.model.Pencil;
import hu.kleatech.bead1.model.PencilCase;
import hu.kleatech.bead1.model.User;
import hu.kleatech.bead1.service.PencilCaseService;
import hu.kleatech.bead1.service.PencilService;
import hu.kleatech.bead1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Bead1Application implements CommandLineRunner {

	@Autowired
	UserService userService;
	@Autowired
	PencilCaseService pencilCaseService;
	@Autowired
	PencilService pencilService;

	public static void main(String[] args) {
		SpringApplication.run(Bead1Application.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		User bela = userService.addUser("Bela", "bela123", "jelszo");
		PencilCase testPencilCase = pencilCaseService.addPencilCase(bela);
		Pencil grafitceruza = pencilService.addPencil(Color.GRAPHITE, "generic", 100, 0, testPencilCase);
		pencilService.sharpenPencil(grafitceruza, 100);
	}
}
