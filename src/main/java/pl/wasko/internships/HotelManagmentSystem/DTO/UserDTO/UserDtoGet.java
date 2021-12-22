package pl.wasko.internships.HotelManagmentSystem.DTO.UserDTO;

import lombok.*;
import pl.wasko.internships.HotelManagmentSystem.DTO.BookingDTO.BookingDTOslim;
import pl.wasko.internships.HotelManagmentSystem.DTO.PaymentDTO.PaymentDtoGet;
import pl.wasko.internships.HotelManagmentSystem.DTO.PaymentDTO.PaymentDtoPost;
import pl.wasko.internships.HotelManagmentSystem.DTO.PaymentDTO.PaymentDtoSlim;
import pl.wasko.internships.HotelManagmentSystem.DTO.RoleDTO.RoleDtoPost;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDtoGet {
    @NotNull(message = "Id cannot be an empty value")
    private Integer id;

    @Size(min = 3, message = "Name should be at least 3 characters")
    @NotNull(message = "Name cannot be an empty value")
    private String name;

    @Size(min = 3, message = "Last name should be at least 3 characters")
    @NotNull(message = "Last name cannot be an empty value")
    private String lastName;

    @Size(min = 5, message = "Phone number should be at least 5 characters")
    @NotNull(message = "Phone numbe cannot be an empty value")
    private String phone;

    @Size(min = 5, message = "Login should be at least 5 characters")
    @NotNull(message = "Login cannot be an empty value")
    private String login;

    @Size(min = 5, message = "Password should be at least 5 characters")
    @NotNull(message = "Password cannot be an empty value")
    private String password;

    private RoleDtoPost role;

    List<BookingDTOslim> bookings;

    List<PaymentDtoSlim> payments;





}
