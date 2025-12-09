package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Cuisine;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import mk.ukim.finki.wp.lab.repository.ChefRepository;
import mk.ukim.finki.wp.lab.model.Chef;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;
    private final ChefRepository chefRepository;

    public DishServiceImpl(DishRepository dishRepository, ChefRepository chefRepository) {
        this.dishRepository = dishRepository;
        this.chefRepository = chefRepository;
    }

    @Override
    public List<Dish> listDishes() {
        return dishRepository.findAll();
    }

    @Override
    public Dish findByDishId(Long dishId) {
        return dishRepository.findById(dishId).orElse(null);
    }

    @Override
    public Dish findById(Long id) {
        return dishRepository.findById(id).orElse(null);
    }

    @Override
    public Dish create(String name, Cuisine cuisine, int preparationTime, Long chefId) {
        Dish dish = new Dish();
        dish.setName(name);
        dish.setCuisine(cuisine);
        dish.setPreparationTime(preparationTime);
        if (chefId != null) {
            Chef chef = chefRepository.findById(chefId).orElse(null);
            dish.setChef(chef);
        }
        return dishRepository.save(dish);
    }

    @Override
    public Dish update(Long dishId, String name, Cuisine cuisine, int preparationTime, Long chefId) {
        Dish dish = dishRepository.findById(dishId).orElse(null);
        if (dish != null) {
            dish.setName(name);
            dish.setCuisine(cuisine);
            dish.setPreparationTime(preparationTime);
            if (chefId != null) {
                Chef chef = chefRepository.findById(chefId).orElse(null);
                dish.setChef(chef);
            } else {
                dish.setChef(null);
            }
            return dishRepository.save(dish);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        dishRepository.deleteById(id);
    }
}
