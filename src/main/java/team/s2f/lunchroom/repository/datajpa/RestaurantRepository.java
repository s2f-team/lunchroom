package team.s2f.lunchroom.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import team.s2f.lunchroom.model.Restaurant;

import java.time.LocalDate;
import java.util.List;


public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    Restaurant save(Restaurant restaurant);

 //   void delete(Integer id);

/*    Restaurant getById(Integer id);

    Restaurant getByName(String name);

    List<Restaurant> getAll();

    List<Restaurant> getActualWithMenu(LocalDate date);*/
}
