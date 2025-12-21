package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Cuisine;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

 
// Any Spring @Component that implements CommandLineRunner is executed after the application context loads, automatically.
// No controller calls it.
// No manual invocation.
// Spring runs this for you.

@Component
public class DbSeeder implements CommandLineRunner {

    private final ChefService chefService;
    private final DishService dishServ;

    public DbSeeder(ChefService chefService, DishService dishServ) {
        this.chefService = chefService;
        this.dishServ = dishServ;
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

         dishServ.create("Beef Wellington", Cuisine.WESTERN, 45,1L);
         dishServ.create("Pasta Carbonara", Cuisine.MEDITERRANEAN, 25,2L);
         dishServ.create("Chicken Tikka Masala", Cuisine.ASIAN, 35,3L);
         dishServ.create("Sushi", Cuisine.ASIAN, 50,4L);
        dishServ.create("Falafel Bowl", Cuisine.MIDDLE_EASTERN, 20,5L);
    }
}
