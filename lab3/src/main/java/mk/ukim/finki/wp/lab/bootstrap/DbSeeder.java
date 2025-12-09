package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.service.ChefService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

 

@Component
public class DbSeeder implements CommandLineRunner {

    private final ChefService chefService;

    public DbSeeder(ChefService chefService) {
        this.chefService = chefService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!chefService.listChefs().isEmpty()) {
            return; // already seeded
        }

        chefService.create("Gordon", "Ramsay", "World-renowned chef with 16 Michelin stars");
        chefService.create("Jamie", "Oliver", "Famous for healthy food");
        chefService.create("Alice", "Waters", "Organic food pioneer");
        chefService.create("Thomas", "Keller", "Michelin starred chef");
        chefService.create("Massimo", "Bottura", "Italian cuisine master");
    }
}
