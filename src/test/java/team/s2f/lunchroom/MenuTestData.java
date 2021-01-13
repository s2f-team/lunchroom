package team.s2f.lunchroom;

import team.s2f.lunchroom.model.Menu;

import java.time.LocalDate;

public class MenuTestData {
    public static final Menu menu_fish_house = new Menu(100008, LocalDate.now(), DishTestData.dishes_fish_house, RestaurantTestData.fish_house);
    public static final Menu menu_masterskaya = new Menu(100011, LocalDate.now(), DishTestData.dishes_masterskaya, RestaurantTestData.masterskaya);
}
