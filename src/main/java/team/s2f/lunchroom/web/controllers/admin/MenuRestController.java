package team.s2f.lunchroom.web.controllers.admin;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import team.s2f.lunchroom.model.Menu;
import team.s2f.lunchroom.service.MenuService;

import java.time.LocalDate;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("rest/admin/restaurants")
public class MenuRestController {
    private static final Logger log = getLogger(MenuRestController.class);

    private final MenuService menuService;

    @Autowired
    public MenuRestController(MenuService menuService) {
        this.menuService = menuService;
    }

    //Get Menu with dishes by restaurantId
    @GetMapping(value = "/{id}/menu", produces = MediaType.APPLICATION_JSON_VALUE)
    public Menu getWithDishesByRestaurantId(@PathVariable int id) {
        log.info("Get menu with dishes by restaurant id {}.", id);
        return menuService.getByRestaurant(id, LocalDate.now());
    }

    //Delete menu with dishes
    @DeleteMapping("/menu/{id}")
    public void delete(@PathVariable int id) {
        log.info("Delete menu by id {}", id);
        menuService.delete(id);
    }
}
