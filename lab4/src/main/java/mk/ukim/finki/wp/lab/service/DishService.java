package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Cuisine;
import mk.ukim.finki.wp.lab.model.Dish;
import java.util.List;

public interface DishService {

    List<Dish> listDishes();

    Dish findByDishId(Long dishId);

    Dish findById(Long id);

    Dish create(String name, Cuisine cuisine, int preparationTime, Long chefId);

    Dish update(Long id, String name, Cuisine cuisine, int preparationTime, Long chefId);

    void delete(Long id);

}
