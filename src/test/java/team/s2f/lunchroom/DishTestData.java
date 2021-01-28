package team.s2f.lunchroom;

import team.s2f.lunchroom.model.Dish;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DishTestData {
    public static final TestMatcher<Dish> DISH_MATCHER = TestMatcher.usingEqualsComparator(Dish.class);

    public static final int NOT_FOUND = 10;
    public static final int DISH1_ID = 100012;

    public static final Dish dish1 = new Dish(100012, "Fish", 150.00, MenuTestData.menu_fish_house);
    public static final Dish dish2 = new Dish(100013, "Soup", 50.00, MenuTestData.menu_fish_house);
    public static final Dish dish3 = new Dish(100014, "Juice", 70.00, MenuTestData.menu_fish_house);

    public static final Dish dish4 = new Dish(100019, "Borsch", 70.00, MenuTestData.menu_masterskaya);
    public static final Dish dish5 = new Dish(100020, "Steak", 400.00, MenuTestData.menu_masterskaya);
    public static final Dish dish6 = new Dish(100021, "Mors", 100.00, MenuTestData.menu_masterskaya);

    public static final List<Dish> dishes_fish_house = List.of(dish1, dish3, dish2).stream()
            .sorted(Comparator.comparing(Dish::getName))
            .collect(Collectors.toList());

    public static final List<Dish> dishes_masterskaya = List.of(dish4, dish5, dish6);

    public static Dish getNew() {
        return new Dish(null, "New dish", 100.00, null);
    }

    public static Dish getNewDuplicated() {
        return new Dish(null, dish1.getName(), 40.00, MenuTestData.menu_fish_house);
    }

    public static Dish getUpdated() {
        return new Dish(DISH1_ID, "Updated Fish", 100.00, MenuTestData.menu_fish_house);
    }
}