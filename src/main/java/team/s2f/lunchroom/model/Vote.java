package team.s2f.lunchroom.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data //Generating equals/hashCode implementation but without a call to superclass, even though this class does not extend java.lang.Object. If this is intentional, add '(callSuper=false)' to your type.
@NoArgsConstructor
public class Vote extends AbstractBaseEntity {
    LocalDate date;
    int restaurantId;
    int lunchId;

}
