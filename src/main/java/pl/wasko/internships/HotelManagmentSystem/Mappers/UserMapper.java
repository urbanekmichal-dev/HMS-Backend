package pl.wasko.internships.HotelManagmentSystem.Mappers;


import org.mapstruct.Mapper;
import pl.wasko.internships.HotelManagmentSystem.DTO.UserDTO.UserDtoGet;
import pl.wasko.internships.HotelManagmentSystem.Entities.UserEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper  {
    List<UserDtoGet> usersToDTO(List<UserEntity> users);
    UserDtoGet userToDTO(UserEntity userEntity);
}
