package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;


@Entity
@Data
@Table(name = "chefs")
public class Chef {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String firstName;
    private String lastName;
    private String bio;
    
    @OneToMany(mappedBy = "chef", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Dish> dishes;

    public Chef() {
    }

    public Chef(String firstName, String lastName, String bio, List<Dish> dishes) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.dishes = dishes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chef)) return false;
        Chef chef = (Chef) o;
        return Objects.equals(this.id, chef.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}