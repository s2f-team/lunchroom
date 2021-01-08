package team.s2f.lunchroom.web.restaurant;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import team.s2f.lunchroom.model.Menu;
import team.s2f.lunchroom.service.MenuService;

import java.awt.*;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("/menu")
public class MenuRestController {
    private static final Logger log = getLogger(MenuRestController.class);

    @Autowired
    private MenuService menuService;

   /* @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Menu> getAll() {
        log.info("MenuRestController - getAll()");
        List<Menu> menus = menuService.getAll();
        System.out.println(menus);
        return menus;
    }

    @GetMapping(value = "/rest", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu> getWithRest() {
        List<Menu> menus = menuService.getAllWithRest();
        System.out.println(menus);
        return menus;
    }*/
}
