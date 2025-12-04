package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Cuisine;
import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

        public static List<Chef> chefs = new ArrayList<>();
        public static List<Dish> dishes = new ArrayList<>();

        @PostConstruct
        public void init() {
            chefs.add(new Chef(1L, "Gordon", "Ramsay", "World-renowned chef with 16 Michelin stars", new ArrayList<>()));
            chefs.add(new Chef(2L, "Jamie", "Oliver", "Famous for healthy food", new ArrayList<>()));
            chefs.add(new Chef(3L, "Alice", "Waters", "Organic food pioneer", new ArrayList<>()));
            chefs.add(new Chef(4L, "Thomas", "Keller", "Michelin starred chef", new ArrayList<>()));
            chefs.add(new Chef(5L, "Massimo", "Bottura", "Italian cuisine master", new ArrayList<>()));

            dishes.add(new Dish("1", "Beef Wellington", Cuisine.WESTERN, 45));
            dishes.add(new Dish("2", "Pasta Carbonara", Cuisine.MEDITERRANEAN, 25));
            dishes.add(new Dish("3", "Chicken Tikka Masala", Cuisine.ASIAN, 35));
            dishes.add(new Dish("4", "Sushi", Cuisine.ASIAN, 50));
            dishes.add(new Dish("5", "Falafel Bowl", Cuisine.MIDDLE_EASTERN, 20));
        }
    }

