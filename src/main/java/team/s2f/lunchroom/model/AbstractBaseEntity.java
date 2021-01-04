package team.s2f.lunchroom.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/*@ToString, @EqualsAndHashCode,
@Getter on all fields, @Setter on all non-final fields,
@RequiredArgsConstructor! */
@Data
@NoArgsConstructor
public abstract class AbstractBaseEntity {
    public static final int START_SEQ = 100000;

    protected Integer id;
}
