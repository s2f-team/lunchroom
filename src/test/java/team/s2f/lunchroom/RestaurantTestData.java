package team.s2f.lunchroom;

import team.s2f.lunchroom.model.Restaurant;

public class RestaurantTestData {
    public static final int NOT_FOUND = 10;

    public static final Restaurant fish_house = new Restaurant(100003, "Fish House", "+7(999)111-22-33", "Moscow, Mayakovskaya, 10", "www.fishhouse.ru", MenuTestData.menu_fish_house);
    public static final Restaurant masterskaya = new Restaurant(100006, "Masterskaya", "+7(495)975-45-23", "Moscow, Myasnitskaya, 22", "www.masterskaya.ru", MenuTestData.menu_masterskaya);
    public static final Restaurant bad_rest = new Restaurant(100007, "Bad rest", "+7(495)975-46-23", "Moscow, Nitskaya, 13", "www.bad.ru", null);

}
