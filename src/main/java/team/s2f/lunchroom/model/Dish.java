package team.s2f.lunchroom.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "dish", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "menu_id"}, name = "dish_unique_menu_name_idx")})
//CREATE UNIQUE INDEX dish_unique_menu_name_idx ON dish (name, menu_id);
public class Dish extends AbstractBaseEntity {

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @Column(name = "price", nullable = false)
    @NotNull
    @Range(min = 10, max = 500)
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "menu_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Menu menu;

    public Dish(String name, Integer price, Menu menu) {
        this(null, name, price, menu);
    }

    public Dish(Integer id, String name, Integer price, Menu menu) {
        super(id);
        this.name = name;
        this.price = price;
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
