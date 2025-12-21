package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Dish;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findAll();
    Optional<Dish> findById(Long id);
    Dish save(Dish dish);
    void deleteById(Long id);
    List<Dish> findAllByChef_Id(Long chefId);
}
