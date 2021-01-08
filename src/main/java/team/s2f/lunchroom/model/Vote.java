package team.s2f.lunchroom.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Data
//Generating equals/hashCode implementation but without a call to superclass, even though this class does not extend java.lang.Object. If this is intentional, add '(callSuper=false)' to your type.
@NoArgsConstructor
@Entity
@Table(name = "votes")
public class Vote extends AbstractBaseEntity {
    @Column(name = "date")
    private LocalDate date = LocalDate.now();

    @Column(name = "user_id")
    private int userId;

    @Column(name = "rest_id")
    private int restaurantId;

    @Column(name = "lunch_id")
    private int lunchId;
}
