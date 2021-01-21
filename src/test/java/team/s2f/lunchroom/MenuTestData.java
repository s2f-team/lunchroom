package team.s2f.lunchroom;

import team.s2f.lunchroom.model.Menu;

import java.time.LocalDate;

public class MenuTestData {
    public static final TestMatcher<Menu> MENU_MATCHER = TestMatcher.usingIgnoringFieldsComparator(Menu.class, "dishes", "restaurant");

    public static final int NOT_FOUND = 10;
    public static final int MENU_FISH_HOUSE_ID = 100008;

    public static final Menu menu_fish_house = new Menu(100008, LocalDate.now(), DishTestData.dishes_fish_house, RestaurantTestData.fish_house);
    public static final Menu menu_masterskaya = new Menu(100011, LocalDate.now(), DishTestData.dishes_masterskaya, RestaurantTestData.masterskaya);

    public static Menu getNew() {
        return new Menu(null, LocalDate.now(), null, RestaurantTestData.fish_house);
    }
}
