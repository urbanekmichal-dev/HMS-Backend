package pl.wasko.internships.HotelManagmentSystem.Securityy.mappers;
import org.mapstruct.Mapper;
import pl.wasko.internships.HotelManagmentSystem.DTO.UserDTO.UserDtoGet;
import pl.wasko.internships.HotelManagmentSystem.Entities.UserEntity;
import pl.wasko.internships.HotelManagmentSystem.Securityy.dto.UserResponse;
import pl.wasko.internships.HotelManagmentSystem.Securityy.model.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    UserResponse userToDTO(User user);
    List<UserResponse> usersToDTO(List<User> users);
}

