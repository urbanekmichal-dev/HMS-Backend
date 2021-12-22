package pl.wasko.internships.HotelManagmentSystem.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Data
@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Builder
@Table(name = "Users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String name;
    private String lastName;
    private String phone;
    private String login;
    private String password;

    @JsonManagedReference
    @OneToMany(mappedBy="user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<BookingEntity> bookings = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy="user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<PaymentEntity> payments = new ArrayList<>();

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEnity role;

    public UserEntity(String name, String lastName, String phone, String login, String password) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.login = login;
        this.password = password;
    }


}
