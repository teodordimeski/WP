package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data

public class Dish {
    private String dishId;
    private String name;
    private String cuisine;
    private int preparationTime;

    public Dish() {
    }

    public Dish(String dishId, String name, String cuisine, int preparationTime) {
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


