package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Cuisine;
import mk.ukim.finki.wp.lab.model.Dish;
import java.util.List;

public interface DishService {

    List<Dish> listDishes();

    Dish findByDishId(String dishId);

    Dish findById(Long id);

    Dish create(String dishId, String name, Cuisine cuisine, int preparationTime);

    Dish update(Long id, String dishId, String name, Cuisine cuisine, int preparationTime);

    void delete(Long id);

}
