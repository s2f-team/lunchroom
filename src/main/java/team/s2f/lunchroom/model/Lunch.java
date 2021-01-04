package team.s2f.lunchroom.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class Lunch extends AbstractBaseEntity {
    LocalDate date;
    List<Dish> dishes;
    Integer restaurant_id;
}
