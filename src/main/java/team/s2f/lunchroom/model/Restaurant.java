package team.s2f.lunchroom.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Restaurant extends AbstractBaseEntity {
    String name;
    String phone;
    String address;
    String website;
    Lunch lunch;
}
