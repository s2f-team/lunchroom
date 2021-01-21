package team.s2f.lunchroom;

import team.s2f.lunchroom.model.Restaurant;

import java.util.List;

public class RestaurantTestData {
    public static final TestMatcher<Restaurant> RESTAURANT_MATCHER = TestMatcher.usingEqualsComparator(Restaurant.class);

    public static final int NOT_FOUND = 10;
    public static final int FISH_HOUSE_ID = 100003;

    public static final Restaurant fish_house = new Restaurant(100003, "Fish House", "+7(999)111-22-33", "Moscow, Mayakovskaya, 10", "www.fishhouse.ru");
    public static final Restaurant meat = new Restaurant(100004, "Мясо", "+7(495)111-23-45", "Moscow, Sretenka, 2", "www.meat.ru");
    public static final Restaurant coffee = new Restaurant(100005, "Coffee shop", "+7(499)125-34-23", "Moscow, Tulskaya, 17", "www.coffee.ru");
    public static final Restaurant masterskaya = new Restaurant(100006, "Masterskaya", "+7(495)975-45-23", "Moscow, Myasnitskaya, 22", "www.masterskaya.ru");
    public static final Restaurant bad_rest = new Restaurant(100007, "Bad rest", "+7(495)975-46-23", "Moscow, Nitskaya, 13", "www.bad.ru");

    public static Restaurant getNew() {
        return new Restaurant(null, "New", "+7(000)975-46-23", "Moscow, Leskova, 27", "website");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(FISH_HOUSE_ID, "Updated", "+7(999)111-72-33", "Moscow, Leskova, 28", "website");
    }

    public static List<Restaurant> getAll = List.of(fish_house, meat, coffee, masterskaya, bad_rest);
}