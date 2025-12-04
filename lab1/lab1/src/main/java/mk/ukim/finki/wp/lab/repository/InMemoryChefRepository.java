package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Chef;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryChefRepository implements ChefRepository {

    @Override
    public List<Chef> findAll() {
        return DataHolder.chefs;
    }

    @Override
    public Optional<Chef> findById(Long id) {
        return DataHolder.chefs.stream().filter(c -> c.getId()==id).findFirst();
    }

    @Override
    public Chef save(Chef chef) {
        if(DataHolder.chefs.contains(chef)) {
            int idx = DataHolder.chefs.indexOf(chef);
            DataHolder.chefs.set(idx, chef);
        } else {
            DataHolder.chefs.add(chef);
        }
        return chef;
    }
}
