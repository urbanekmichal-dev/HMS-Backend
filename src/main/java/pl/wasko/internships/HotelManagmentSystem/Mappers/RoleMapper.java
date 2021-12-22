package pl.wasko.internships.HotelManagmentSystem.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.wasko.internships.HotelManagmentSystem.DTO.RoleDTO.RoleDtoGet;
import pl.wasko.internships.HotelManagmentSystem.DTO.RoleDTO.RoleDtoPost;
import pl.wasko.internships.HotelManagmentSystem.DTO.UserDTO.UserDtoGet;
import pl.wasko.internships.HotelManagmentSystem.Entities.RoleEnity;
import pl.wasko.internships.HotelManagmentSystem.Entities.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    List<RoleDtoGet> rolesWithUsersToDTO(List<RoleEnity> roles);
    RoleDtoGet roleToDTO(RoleEnity roleEnity);

}


