package team.s2f.lunchroom.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.s2f.lunchroom.HasId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "menu")
public class Menu extends AbstractBaseEntity implements HasId {
    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate date;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "menu")
    @JsonManagedReference
    private List<Dish> dishes;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "rest_id")
    private Restaurant restaurant;

    public Menu(LocalDate date) {
        this(null, date, null);
    }

    public Menu(LocalDate date, Restaurant restaurant) {
        this(null, date, null, restaurant);
    }

    public Menu(Integer id, LocalDate date, List<Dish> dishes) {
        super(id);
        this.date = date;
        this.dishes = dishes;
    }

    public Menu(Integer id, LocalDate date, List<Dish> dishes, Restaurant restaurant) {
        super(id);
        this.date = date;
        this.dishes = dishes;
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", date=" + date +
                ", dishes=" + dishes +
                ", restaurant=" + restaurant +
                '}';
    }
}
