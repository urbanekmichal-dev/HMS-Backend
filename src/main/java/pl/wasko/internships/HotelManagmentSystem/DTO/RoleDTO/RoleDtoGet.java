package pl.wasko.internships.HotelManagmentSystem.DTO.RoleDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.wasko.internships.HotelManagmentSystem.DTO.UserDTO.UserDTOslim;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDtoGet {
    private Long id;
    private String title;
    private List<UserDTOslim> users;
}
