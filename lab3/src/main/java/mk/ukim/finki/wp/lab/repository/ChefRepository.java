package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Chef;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChefRepository  extends JpaRepository<Chef, Long> {
    List<Chef> findAll();
    Optional<Chef> findById(Long id);//Optional<T> is a container object that may or may not contain a non-null value of type T
    Chef save(Chef chef);
}