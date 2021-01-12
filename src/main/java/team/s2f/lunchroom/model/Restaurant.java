package team.s2f.lunchroom.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractBaseEntity {

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    @Column(name = "phone", nullable = false)
    @NotBlank
    @Size(min = 10, max = 16)
    private String phone;

    @Column(name = "address", nullable = false)
    @NotBlank
    @Size(min = 10, max = 300)
    private String address;

    @Column(name = "website")
    @Size(min = 2, max = 50)
    private String website;

    @OneToOne(mappedBy = "restaurant", cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY, optional = false)
    @JsonBackReference
    private Menu menu;

    public Restaurant(String name, String phone, String address, String website) {
        this(null, name, phone, address, website, null);
    }

    public Restaurant(Integer id, String name, String phone, String address, String website, Menu menu) {
        super(id);
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.website = website;
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
