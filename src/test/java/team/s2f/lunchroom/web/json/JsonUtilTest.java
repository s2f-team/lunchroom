package team.s2f.lunchroom.web.json;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import team.s2f.lunchroom.DishTestData;
import team.s2f.lunchroom.UserTestData;
import team.s2f.lunchroom.model.Dish;
import team.s2f.lunchroom.model.User;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;

public class JsonUtilTest {

    @Test
    void readWriteValue() {
        String json = JsonUtil.writeValue(DishTestData.dish1);
        System.out.println(json);
        Dish dish = JsonUtil.readValue(json, Dish.class);
        DishTestData.DISH_MATCHER.assertMatch(dish, DishTestData.dish1);
    }

    @Test
    void readWriteValues() {
        String json = JsonUtil.writeValue(DishTestData.dishes_fish_house);
        System.out.println(json);
        List<Dish> dishes = JsonUtil.readValues(json, Dish.class);
        DishTestData.DISH_MATCHER.assertMatch(dishes, DishTestData.dishes_fish_house);
    }

    @Test
    void writeOnlyAccess() throws Exception {
        String json = JsonUtil.writeValue(UserTestData.user1);
        System.out.println(json);
        MatcherAssert.assertThat(json, not(containsString("password")));
        String jsonWithPass = UserTestData.jsonWithPassword(UserTestData.user1, "newPass");
        System.out.println(jsonWithPass);
        User user = JsonUtil.readValue(jsonWithPass, User.class);
        Assertions.assertEquals(user.getPassword(), "newPass");
    }
}
