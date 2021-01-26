package team.s2f.lunchroom.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import team.s2f.lunchroom.model.Menu;
import team.s2f.lunchroom.repository.MenuRepository;
import team.s2f.lunchroom.repository.RestaurantRepository;
import team.s2f.lunchroom.util.ValidationUtil;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public Menu create(Menu menu, int restaurantId) {
        Assert.notNull(menu, "Menu must not be null.");
        ValidationUtil.checkNotFoundWithId(restaurantRepository.getById(restaurantId), restaurantId);
        return save(menu, restaurantId);
    }

    public void delete(int id) {
        ValidationUtil.checkSingleModification(menuRepository.delete(id), "Menu id=" + id + " missed.");

    }

    public Menu getByRestaurant(int restaurantId, LocalDate date) {
        return ValidationUtil.checkNotFoundWithId(menuRepository.getByRestaurantIdAndDate(restaurantId, date), restaurantId);
    }

    public List<Menu> getAllWithRestaurants(LocalDate date) {
        return menuRepository.getAllWithRestaurant(date);
    }


    Menu save(Menu menu, int restaurantId) {
        if (!menu.isNew()) {
            return null;
        }
        menu.setRestaurant(restaurantRepository.getOne(restaurantId));
        return menuRepository.save(menu);
    }

    /*
    *   @Override
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
    }*/
}
