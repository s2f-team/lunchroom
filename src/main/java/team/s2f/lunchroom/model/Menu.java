package team.s2f.lunchroom.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import team.s2f.lunchroom.HasId;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "menu")
public class Menu extends AbstractBaseEntity implements HasId {
    @Column(name = "date")
    private LocalDate date;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "menu")
    @JsonManagedReference
    private List<Dish> dishes;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "rest_id")
    @JsonManagedReference
    private Restaurant restaurant;

    public Menu(LocalDate date) {
        this(null, date, null);
    }

    public Menu(Integer id, LocalDate date, List<Dish> dishes) {
        super(id);
        this.date = date;
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", date=" + date +
                ", dishes=" + dishes +
                '}';
    }
}
