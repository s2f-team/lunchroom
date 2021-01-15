package team.s2f.lunchroom.repository;

import team.s2f.lunchroom.model.Menu;

import java.time.LocalDate;
import java.util.List;


public interface MenuRepository {
    Menu create(Menu menu, int restaurantId);

    boolean delete(int id);

    Menu getByRestaurantId(int restaurantId, LocalDate date);

    List<Menu> getAll();

    List<Menu> getAllWithRestaurant(LocalDate date);

}
