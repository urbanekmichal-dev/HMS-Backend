package pl.wasko.internships.HotelManagmentSystem.DTO.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTOslim {
    //@NotNull(message = "Id cannot be an empty value")
    private Integer id;

    //@Size(min = 3, message = "Name should be at least 3 characters")
    //@NotNull(message = "Name cannot be an empty value")
    private String name;

   // @Size(min = 3, message = "Last name should be at least 3 characters")
    //@NotNull(message = "Last name cannot be an empty value")
    private String lastName;

    //@Size(min = 5, message = "Phone number should be at least 5 characters")
    //@NotNull(message = "Phone numbe cannot be an empty value")
    private String phone;

    //@Size(min = 5, message = "Login should be at least 5 characters")
    //@NotNull(message = "Login cannot be an empty value")
    private String login;

    //@Size(min = 5, message = "Password should be at least 5 characters")
   // @NotNull(message = "Password cannot be an empty value")
    private String password;

}
