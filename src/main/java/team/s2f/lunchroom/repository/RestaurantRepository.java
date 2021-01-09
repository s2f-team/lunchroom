package team.s2f.lunchroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import team.s2f.lunchroom.model.Restaurant;

import java.time.LocalDate;
import java.util.List;


public interface RestaurantRepository {
    Restaurant save(Restaurant restaurant);

    boolean delete(int id);

    Restaurant getOne(int id);

    Restaurant getById(Integer id);

    Restaurant getByName(String name);

    List<Restaurant> getAll();

    List<Restaurant> getActualWithMenu(LocalDate date);
}
