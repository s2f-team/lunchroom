package team.s2f.lunchroom.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Dish extends AbstractBaseEntity {
    String name;
    Integer price;
    Integer lunchId;
}
