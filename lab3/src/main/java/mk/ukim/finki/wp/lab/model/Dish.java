package mk.ukim.finki.wp.lab.model;

import lombok.Data;

@Data
public class Dish {
    private static long counter = 1L;

    private Long id;
    private String dishId;
    private String name;
    private Cuisine cuisine;
    private int preparationTime;

    public Dish() {
        this.id = counter++;
    }

    public Dish(String dishId, String name, Cuisine cuisine, int preparationTime) {
        this.id = counter++;
        this.dishId = dishId;
        this.name = name;
        this.cuisine = cuisine;
        this.preparationTime = preparationTime;
    }

    public String getDishId() {
        return dishId;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }
}
