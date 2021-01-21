package team.s2f.lunchroom.web.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import team.s2f.lunchroom.MenuTestData;
import team.s2f.lunchroom.TestUtil;
import team.s2f.lunchroom.UserTestData;
import team.s2f.lunchroom.model.Dish;
import team.s2f.lunchroom.service.DishService;
import team.s2f.lunchroom.util.exception.ErrorType;
import team.s2f.lunchroom.util.exception.NotFoundException;
import team.s2f.lunchroom.web.AbstractControllerTest;
import team.s2f.lunchroom.web.json.JsonUtil;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static team.s2f.lunchroom.DishTestData.*;

public class DishRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = "/rest/admin/restaurants/";

    @Autowired
    private DishService dishService;


    @Test
    void getByUser() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "100003/menu/100008/dishes/" + DISH1_ID)
                .with(TestUtil.userHttpBasic(UserTestData.user1)))
                .andExpect(status().isForbidden());
    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "100003/menu/100008/dishes/" + DISH1_ID)
                .with(TestUtil.userHttpBasic(UserTestData.admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(dish1));
    }

    @Test
    void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "100003/menu/100008/dishes/" + DISH1_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "100003/menu/100008/dishes/" + NOT_FOUND)
                .with(TestUtil.userHttpBasic(UserTestData.admin)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + "100003/menu/100008/dishes/" + DISH1_ID)
                .with(TestUtil.userHttpBasic(UserTestData.admin)))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> dishService.get(DISH1_ID, MenuTestData.MENU_FISH_HOUSE_ID));
    }

    @Test
    void deleteNotAdmin() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + "100003/menu/100008/dishes/" + DISH1_ID)
                .with(TestUtil.userHttpBasic(UserTestData.user1)))
                .andExpect(status().isForbidden());
    }

    @Test
    void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + "100003/menu/100008/dishes/" + NOT_FOUND)
                .with(TestUtil.userHttpBasic(UserTestData.admin)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void update() throws Exception {
        Dish updated = getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + "100003/menu/100008/dishes/" + DISH1_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(TestUtil.userHttpBasic(UserTestData.admin)))
                .andExpect(status().isNoContent());

        DISH_MATCHER.assertMatch(dishService.get(DISH1_ID, MenuTestData.MENU_FISH_HOUSE_ID), updated);
    }

    @Test
    void updateInvalid() throws Exception {
        Dish invalid = new Dish(DISH1_ID, null, 0, MenuTestData.menu_fish_house);

        perform(MockMvcRequestBuilders.put(REST_URL + "100003/menu/100008/dishes/" + DISH1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(TestUtil.userHttpBasic(UserTestData.admin)))
                .andDo(print())
                .andExpect(status().is(422))
                .andExpect(errorType(ErrorType.VALIDATION_ERROR));
    }

    @Test
    void create() throws Exception {
        Dish newDish = getNew();

        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + "100003/menu/100008/dishes/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDish))
                .with(TestUtil.userHttpBasic(UserTestData.admin)));

        Dish created = TestUtil.readFromJson(action, Dish.class);
        int newId = created.id();

        newDish.setId(newId);
        DISH_MATCHER.assertMatch(created, newDish);
        DISH_MATCHER.assertMatch(dishService.get(newId, 100008), newDish);
    }

    @Test
    void createInvalid() throws Exception {
        Dish invalid = new Dish(null, null, 0, null);

        perform(MockMvcRequestBuilders.post(REST_URL + "100003/menu/100008/dishes/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(TestUtil.userHttpBasic(UserTestData.admin)))
                .andDo(print())
                .andExpect(status().is(422))
                .andExpect(errorType(ErrorType.VALIDATION_ERROR));
    }

    @Test
    void getAllByMenu() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "100003/menu/100008/dishes/")
                .with(TestUtil.userHttpBasic(UserTestData.admin)))
                .andExpect(status().is(200))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(dishes_fish_house));
    }

    @Test
    void getAllByIncorrectMenu() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "100003/menu/10/dishes/")
                .with(TestUtil.userHttpBasic(UserTestData.admin)))
                .andExpect(status().isUnprocessableEntity());
    }
}