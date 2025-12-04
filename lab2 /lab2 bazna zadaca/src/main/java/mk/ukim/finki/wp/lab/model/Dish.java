package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
public class Dish {
    private static long counter = 1L;

    private Long id;
    private String dishId;
    private String name;
    private String cuisine;
    private int preparationTime;

    public Dish() {
        this.id = counter++;
    }

    public Dish(String dishId, String name, String cuisine, int preparationTime) {
        this.id = counter++;
        this.dishId = dishId;
        this.name = name;
        this.cuisine = cuisine;
        this.preparationTime = preparationTime;
    }

    public String getDishId() {
        return dishId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }
}


