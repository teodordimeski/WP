package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.ChefRepository;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChefServiceImpl implements ChefService {

    private final ChefRepository chefRepository;
    private final DishRepository dishRepository;

    public ChefServiceImpl(ChefRepository chefRepository, DishRepository dishRepository) {
        this.chefRepository = chefRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    public List<Chef> listChefs() {
        return chefRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Chef findById(Long id) {
        Chef chef = chefRepository.findById(id).orElse(null);
        if (chef != null && chef.getDishes() != null) {
            // initialize lazy collection while still in transaction
            chef.getDishes().size();
        }
        return chef;
    }

    @Override
    public Chef addDishToChef(Long chefId, Long dishId) {
        Chef chef = findById(chefId);
        Dish dish = dishRepository.findById(dishId).orElse(null);
        if (chef != null && dish != null) {
            chef.getDishes().add(dish);
            chefRepository.save(chef);
            return chef;
        }
        return chef;
    }

    @Override
    public Chef create(String firstName, String lastName, String bio) {
        Chef chef = new Chef();
        chef.setFirstName(firstName);
        chef.setLastName(lastName);
        chef.setBio(bio);
        chef.setDishes(new ArrayList<>());
        return chefRepository.save(chef);
    }


}
