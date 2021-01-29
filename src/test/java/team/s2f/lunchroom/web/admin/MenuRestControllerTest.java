package team.s2f.lunchroom.web.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import team.s2f.lunchroom.MenuTestData;
import team.s2f.lunchroom.RestaurantTestData;
import team.s2f.lunchroom.TestUtil;
import team.s2f.lunchroom.UserTestData;
import team.s2f.lunchroom.model.Menu;
import team.s2f.lunchroom.service.MenuService;
import team.s2f.lunchroom.web.AbstractControllerTest;
import team.s2f.lunchroom.web.json.JsonUtil;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MenuRestControllerTest extends AbstractControllerTest {

    @Autowired
    private MenuService menuService;

    @Test
    void create() throws Exception {
        Menu newMenu = MenuTestData.getNew();

        ResultActions action = perform(MockMvcRequestBuilders.post("/rest/admin/restaurants/" + RestaurantTestData.FISH_HOUSE_ID + "/menus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newMenu))
                .with(TestUtil.userHttpBasic(UserTestData.admin)))
                .andDo(print())
                .andExpect(status().is(201));

        Menu created = TestUtil.readFromJson(action, Menu.class);
        int newId = created.id();

        newMenu.setId(newId);

        MenuTestData.MENU_MATCHER.assertMatch(created, newMenu);
        MenuTestData.MENU_MATCHER.assertMatch(menuService.get(newId), newMenu);
    }
}