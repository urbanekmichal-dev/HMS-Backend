package pl.wasko.internships.HotelManagmentSystem.Securityy.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.wasko.internships.HotelManagmentSystem.Entities.BookingEntity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Userr")
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long userId;
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Password is required")
    private String password;
    @Email
    @NotEmpty(message = "Email is required")
    private String email;
    private Instant created;
    private boolean enabled;
    @NotBlank(message = "Firstname is required")
    private String firstname;
    @NotBlank(message = "Lastname is required")
    private String lastname;
    @NotBlank(message = "Phone is required")
    private String phone;
    @NotBlank(message = "Address is required")
    private String address;
    private Role role;
    private String image;

    @JsonManagedReference
    @OneToMany(mappedBy="owner",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<BookingEntity> bookings = new ArrayList<>();

    public User(@NotBlank(message = "Username is required") String username,
                @NotBlank(message = "Password is required") String password,
                @Email @NotEmpty(message = "Email is required") String email,
                Instant created, boolean enabled, @NotBlank(message = "Firstname is required")
                        String firstname, @NotBlank(message = "Lastname is required") String lastname,
                @NotBlank(message = "Phone is required") String phone, @NotBlank(message = "Address is required")
                        String address, Role role, String image) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.created = created;
        this.enabled = enabled;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.address = address;
        this.role = role;
        this.image = image;
    }
}
