package team.s2f.lunchroom.repository;

import org.springframework.transaction.annotation.Transactional;
import team.s2f.lunchroom.model.Menu;

import java.time.LocalDate;
import java.util.List;


public interface MenuRepository {

    @Transactional
    Menu create(Menu menu, int restaurantId);

    boolean delete(int id);

    Menu getById(int id);

    Menu getByRestaurantId(int restaurantId, LocalDate date);

    List<Menu> getAll();

    List<Menu> getAllWithRestaurant(LocalDate date);

}
