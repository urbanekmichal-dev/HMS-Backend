package pl.wasko.internships.HotelManagmentSystem.Securityy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.wasko.internships.HotelManagmentSystem.Securityy.model.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Integer userId;
    private String email;
    private String username;
    private String firstname;
    private Boolean enabled;
    private String lastname;
    private String phone;
    private String address;
    private Role role;
    private String image;
}
