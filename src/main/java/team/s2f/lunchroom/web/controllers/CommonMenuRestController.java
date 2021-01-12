package team.s2f.lunchroom.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.s2f.lunchroom.model.Menu;
import team.s2f.lunchroom.service.MenuService;

import java.util.List;

@RestController
@RequestMapping("/rest/restaurants/menu")
public class CommonMenuRestController {

    private final MenuService menuService;

    @Autowired
    public CommonMenuRestController(MenuService menuService) {
        this.menuService = menuService;
    }

    //Get Menu with Restaurant and Dishes
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu> getAllWithRestaurants() {
        return menuService.getAllWithRestaurants();
    }
}
