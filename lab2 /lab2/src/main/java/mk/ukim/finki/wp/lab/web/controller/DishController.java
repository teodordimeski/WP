package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Cuisine;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dishes")
public class DishController {

    private final DishService dishService;
    private final ChefService chefService;

    public DishController(DishService dishService, ChefService chefService) {
        this.dishService = dishService;
        this.chefService = chefService;
    }

    @GetMapping
    public String getDishesPage(@RequestParam(required = false) String error, Model model) {
        List<Dish> dishes = dishService.listDishes();
        model.addAttribute("dishes", dishes);
        if (error != null && !error.isEmpty()) {
            model.addAttribute("error", error);
        }
        return "listDishes";
    }

    @GetMapping("/add")
    public String getAddDishPage(Model model) {
        prepareDishForm(model, null);
        return "dish-form";
    }

    @GetMapping("/dish-form")
    public String getAddDishForm(Model model) {
        prepareDishForm(model, null);
        return "dish-form";
    }

    @PostMapping("/add")
    public String saveDish(@RequestParam String dishId,
                           @RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime,
                           Model model) {
        try {
            Cuisine selectedCuisine = Cuisine.valueOf(cuisine);
            dishService.create(dishId, name, selectedCuisine, preparationTime);
            return "redirect:/dishes";
        } catch (IllegalArgumentException ex) {
            prepareDishForm(model, null);
            model.addAttribute("error", "Invalid cuisine selected");
            return "dish-form";
        }
    }

    @GetMapping("/edit/{id}")
    public String getEditDishPage(@PathVariable Long id, Model model) {
        Dish dish = dishService.findById(id);
        if (dish != null) {
            prepareDishForm(model, dish);
            return "dish-form";
        }
        return "redirect:/dishes?error=Dish not found";
    }

    @GetMapping("/dish-form/{id}")
    public String getEditDishForm(@PathVariable Long id, Model model) {
        Dish dish = dishService.findById(id);
        if (dish != null) {
            prepareDishForm(model, dish);
            return "dish-form";
        }
        return "redirect:/dishes?error=DishNotFound";
    }

    @PostMapping("/edit/{id}")
    public String editDish(@PathVariable Long id,
                           @RequestParam String dishId,
                           @RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime,
                           Model model) {
        try {
            Cuisine selectedCuisine = Cuisine.valueOf(cuisine);
            dishService.update(id, dishId, name, selectedCuisine, preparationTime);
            return "redirect:/dishes";
        } catch (IllegalArgumentException ex) {
            Dish dish = dishService.findById(id);
            prepareDishForm(model, dish);
            model.addAttribute("error", "Invalid cuisine selected");
            return "dish-form";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteDish(@PathVariable Long id) {
        dishService.delete(id);
        return "redirect:/dishes";
    }

    @PostMapping("/add-to-chef")
    public String addDishToChef(@RequestParam Long chefId, @RequestParam String dishId) {
        Chef chef = chefService.addDishToChef(chefId, dishId);
        if (chef != null) {
            return "redirect:/listChefs";
        }
        return "redirect:/dishes?error=Failed to add dish to chef";
    }

    @PostMapping("/select-chef")
    public String selectChefForDish(@RequestParam Long chefId, Model model) {
        Chef chef = chefService.findById(chefId);
        if (chef != null) {
            List<Dish> dishes = dishService.listDishes();
            model.addAttribute("selectedChef", chef);
            model.addAttribute("dishes", dishes);
            return "dishesList";
        }
        return "redirect:/listChefs?error=Chef not found";
    }
    private void prepareDishForm(Model model, Dish dish) {
        model.addAttribute("dish", dish);
        model.addAttribute("cuisines", Cuisine.values());
    }
}

