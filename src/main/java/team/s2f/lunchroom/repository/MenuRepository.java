package team.s2f.lunchroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import team.s2f.lunchroom.model.Menu;
import team.s2f.lunchroom.repository.datajpa.MenuCrud;

import java.time.LocalDate;
import java.util.List;


public interface MenuRepository {
    Menu create(Menu menu, int restaurantId);

    boolean delete(int id);

    Menu getByRestaurantId(int restaurantId, LocalDate date);

    List<Menu> getAll();

    List<Menu> getAllWithRestaurant();

}
