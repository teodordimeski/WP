package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "dishes")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @Enumerated(EnumType.STRING)
    private Cuisine cuisine;
    
    private int preparationTime;
    
    @ManyToOne
    @JoinColumn(name = "chef_id")
    private Chef chef;

    public Dish(String name, Cuisine cuisine, int preparationTime) {
        this.name = name;
        this.cuisine = cuisine;
        this.preparationTime = preparationTime;
    }



}
