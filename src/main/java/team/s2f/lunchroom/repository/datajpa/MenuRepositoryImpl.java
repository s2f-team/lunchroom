package team.s2f.lunchroom.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.s2f.lunchroom.model.Menu;
import team.s2f.lunchroom.repository.MenuRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class MenuRepositoryImpl implements MenuRepository {
    private final MenuCrud menuCrud;
    private final RestaurantCrud restaurantCrud;

    @Autowired
    public MenuRepositoryImpl(MenuCrud menuCrud, RestaurantCrud restaurantCrud) {
        this.menuCrud = menuCrud;
        this.restaurantCrud = restaurantCrud;
    }

    @Override
    public Menu create(Menu menu, int restaurantId) {
        if (!menu.isNew()) {
            return null;
        }
        menu.setRestaurant(restaurantCrud.getOne(restaurantId));
        return menuCrud.save(menu);
    }

    @Override
    public boolean delete(int id) {
        return menuCrud.delete(id) != 0;
    }

    @Override
    public Menu getById(int id) {
        return menuCrud.findById(id).orElse(null);
    }

    @Override
    public Menu getByRestaurantId(int restaurantId, LocalDate date) {
        return menuCrud.getByRestaurantId(restaurantId, date);
    }

    @Override
    public List<Menu> getAll() {
        return menuCrud.findAll();
    }

    @Override
    public List<Menu> getAllWithRestaurant(LocalDate date) {
        return menuCrud.getAllWithRestaurant(date);
    }
}
