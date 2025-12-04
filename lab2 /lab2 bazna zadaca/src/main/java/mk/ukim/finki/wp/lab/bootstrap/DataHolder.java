package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
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

            dishes.add(new Dish("1", "Beef Wellington", "British", 45));
            dishes.add(new Dish("2", "Pasta Carbonara", "Italian", 25));
            dishes.add(new Dish("3", "Chicken Tikka Masala", "Indian", 35));
            dishes.add(new Dish("4", "Sushi", "Japanese", 50));
            dishes.add(new Dish("5", "Caesar Salad", "American", 15));
        }
    }

