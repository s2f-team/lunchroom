package team.s2f.lunchroom.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import team.s2f.lunchroom.model.Menu;
import team.s2f.lunchroom.repository.MenuRepository;
import team.s2f.lunchroom.repository.RestaurantRepository;
import team.s2f.lunchroom.util.ValidationUtil;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    @CacheEvict(value = "menus", allEntries = true)
    public Menu create(Menu menu, int restaurantId) {
        Assert.notNull(menu, "Menu must not be null.");
        ValidationUtil.checkNotFoundWithId(restaurantRepository.getById(restaurantId), restaurantId);
        return save(menu, restaurantId);
    }

    @CacheEvict(value = "menus", allEntries = true)
    public void delete(int id) {
        ValidationUtil.checkSingleModification(menuRepository.delete(id), "Menu id=" + id + " missed.");
    }

    public Menu get(int id) {
        return ValidationUtil.checkNotFoundWithId(menuRepository.findById(id), id);
    }

    public Menu getByRestaurant(int restaurantId, LocalDate date) {
        return ValidationUtil.checkNotFoundWithId(menuRepository.getByRestaurantIdAndDate(restaurantId, date), restaurantId);
    }

    @Cacheable("menus")
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
}