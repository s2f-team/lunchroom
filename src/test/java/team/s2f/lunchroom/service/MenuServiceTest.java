package team.s2f.lunchroom.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import team.s2f.lunchroom.DishTestData;
import team.s2f.lunchroom.MenuTestData;
import team.s2f.lunchroom.RestaurantTestData;
import team.s2f.lunchroom.model.Menu;
import team.s2f.lunchroom.util.exception.NotFoundException;

import java.time.LocalDate;

public class MenuServiceTest extends AbstractServiceTest {

    protected MenuService menuService;
    protected DishService dishService;

    @Autowired
    public MenuServiceTest(MenuService menuService, DishService dishService) {
        this.menuService = menuService;
        this.dishService = dishService;
    }

    @Test
    void delete() {
        menuService.delete(MenuTestData.MENU_FISH_HOUSE_ID);
        Assertions.assertThrows(NotFoundException.class, () -> menuService.getByRestaurant(RestaurantTestData.fish_house.getId(), LocalDate.now()));
    }

    @Test
    void deleteNotFound() {
        Assertions.assertThrows(NotFoundException.class, () -> menuService.delete(MenuTestData.NOT_FOUND));
    }

    @Test
    void deleteWithDishes() {
        menuService.delete(MenuTestData.MENU_FISH_HOUSE_ID);
        Assertions.assertThrows(NotFoundException.class, () -> menuService.getByRestaurant(RestaurantTestData.fish_house.getId(), LocalDate.now()));
        Assertions.assertThrows(NotFoundException.class, () -> dishService.getAllByMenuId(MenuTestData.MENU_FISH_HOUSE_ID));
        Assertions.assertThrows(NotFoundException.class, () -> dishService.get(DishTestData.DISH1_ID, MenuTestData.MENU_FISH_HOUSE_ID));
    }

    @Test
    void create() {
        Menu created = menuService.create(MenuTestData.getNew(), RestaurantTestData.bad_rest.id());
        int newId = created.id();
        Menu newMenu = MenuTestData.getNew();
        newMenu.setId(newId);
        MenuTestData.MENU_MATCHER.assertMatch(created, newMenu);
        MenuTestData.MENU_MATCHER.assertMatch(menuService.getByRestaurant(RestaurantTestData.bad_rest.getId(), LocalDate.now()), newMenu);
    }

    @Test
    void createDuplicate(){
        Assertions.assertThrows(DataAccessException.class, () -> menuService.create(MenuTestData.getDuplicate(), RestaurantTestData.FISH_HOUSE_ID));
    }

    @Test
    void createWithIncorrectRestaurant() {
        Assertions.assertThrows(NotFoundException.class, () -> menuService.create(MenuTestData.getNew(), RestaurantTestData.NOT_FOUND));
    }

    @Test
    void getByRestaurant() {
        Menu actual = menuService.getByRestaurant(RestaurantTestData.fish_house.getId(), LocalDate.now());
        MenuTestData.MENU_MATCHER.assertMatch(actual, MenuTestData.menu_fish_house);
    }

    @Test
    void getNotFound() {
        Assertions.assertThrows(NotFoundException.class, () -> menuService.getByRestaurant(RestaurantTestData.NOT_FOUND, LocalDate.now()));
    }

    @Test
    void getWithIncorrectDate() {
        Assertions.assertThrows(NotFoundException.class, () -> menuService.getByRestaurant(RestaurantTestData.NOT_FOUND, null));
    }
}