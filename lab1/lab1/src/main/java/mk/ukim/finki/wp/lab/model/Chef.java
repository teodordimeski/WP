package mk.ukim.finki.wp.lab.model;

import lombok.*;

import java.util.List;
import java.util.Objects;

@Data// includes: @Getter @Setter @ToString @EqualsAndHashCode @RequiredArgsConstructor
public class Chef {
    private Long id;
    private String firstName;
    private String lastName;
    private String bio;
    private List<Dish> dishes;

    public Chef() {
    }

    public Chef(Long id, String firstName, String lastName, String bio, List<Dish> dishes) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.dishes = dishes;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBio() {
        return bio;
    }

    public List<Dish> getDishes() {
        return dishes;


    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setDishes(List<Dish> dishes) {
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