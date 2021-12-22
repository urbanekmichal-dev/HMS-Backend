package pl.wasko.internships.HotelManagmentSystem.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Roles")
@Entity
public class RoleEnity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Long id;
    private String title;

    @JsonManagedReference
    @OneToMany(mappedBy="role",cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    List<UserEntity> users = new ArrayList<>();

    public RoleEnity(String title) {
        this.title = title;
    }
}
