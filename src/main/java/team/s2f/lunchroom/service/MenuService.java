package team.s2f.lunchroom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import team.s2f.lunchroom.model.Menu;
import team.s2f.lunchroom.repository.MenuRepository;
import team.s2f.lunchroom.util.ValidationUtil;

import java.time.LocalDate;
import java.util.List;

@Service
public class MenuService {
    private final MenuRepository menuRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Menu create(Menu menu, int restaurantId) {
        Assert.notNull(menu, "Menu must not be null.");
        return menuRepository.create(menu, restaurantId);
    }

    public void delete(int id) {
        ValidationUtil.checkNotFoundWithId(menuRepository.delete(id), id);
    }

    public Menu getByRestaurant(int restaurantId, LocalDate date) {
        return ValidationUtil.checkNotFoundWithId(menuRepository.getByRestaurantId(restaurantId, date), restaurantId);
    }

    public List<Menu> getAllWithRestaurants() {
        return menuRepository.getAllWithRestaurant();
    }
}
