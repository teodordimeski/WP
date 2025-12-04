package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.service.ChefService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ChefController {

    private final ChefService chefService;

    public ChefController(ChefService chefService) {
        this.chefService = chefService;
    }

    @GetMapping("/listChefs")
    public String getChefsPage(Model model) {
        List<Chef> chefs = chefService.listChefs();
        model.addAttribute("chefs", chefs);
        return "listChefs";
    }

    @PostMapping("/chefDetails")
    public String addDishToChef(@RequestParam Long chefId,
                                @RequestParam String dishId,
                                Model model) {
        try {
            Chef chef = chefService.addDishToChef(chefId, dishId);
            if (chef != null) {
                model.addAttribute("chef", chef);
                return "chefDetails";
            }
            return "redirect:/listChefs?error=Chef or dish not found";
        } catch (Exception e) {
            return "redirect:/listChefs?error=" + e.getMessage();
        }
    }
}

