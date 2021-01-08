package team.s2f.lunchroom.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractBaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "website")
    private String website;

    @OneToOne(mappedBy = "restaurant", cascade = CascadeType.ALL,
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
