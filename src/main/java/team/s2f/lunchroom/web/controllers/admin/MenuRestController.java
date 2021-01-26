package team.s2f.lunchroom.web.controllers.admin;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import team.s2f.lunchroom.model.Menu;
import team.s2f.lunchroom.service.MenuService;

import java.time.LocalDate;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("rest/admin/menus")
public class MenuRestController {
    private static final Logger log = getLogger(MenuRestController.class);

    private final MenuService menuService;

    @Autowired
    public MenuRestController(MenuService menuService) {
        this.menuService = menuService;
    }

    //Delete menu with dishes
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        log.info("Delete menu by id {}", id);
        menuService.delete(id);
    }

    //Get Menu with dishes by restaurantId
    //Мне кажется, это не по REST!!
    @GetMapping(value = "/byrestaurant", produces = MediaType.APPLICATION_JSON_VALUE)
    public Menu getWithDishesByRestaurantId(@RequestParam("id") int restaurantId) {
        log.info("Get menu with dishes by restaurant id {}.", restaurantId);
        return menuService.getByRestaurant(restaurantId, LocalDate.now());
    }

    //Get all menus with dishes and restaurants by date
    @GetMapping(value = "/by", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu> getAllWithRestaurantsByDate(@RequestParam("date") String date) {
        return menuService.getAllWithRestaurants(LocalDate.parse(date));
    }
}