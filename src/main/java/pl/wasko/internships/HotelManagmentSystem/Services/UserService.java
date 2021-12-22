package pl.wasko.internships.HotelManagmentSystem.Services;

import pl.wasko.internships.HotelManagmentSystem.DTO.BookingDTO.BookingDtoGet;
import pl.wasko.internships.HotelManagmentSystem.DTO.RoomDTO.RoomDtoGet;
import pl.wasko.internships.HotelManagmentSystem.DTO.UserDTO.UserDtoGet;
import pl.wasko.internships.HotelManagmentSystem.DTO.UserDTO.UserDtoPost;
import pl.wasko.internships.HotelManagmentSystem.Entities.UserEntity;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.RoleNotFoundException;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {
    UserDtoGet addUser(UserDtoPost userDtoPost) throws RoleNotFoundException;
    UserDtoGet updateUser(UserDtoPost userDtoPost) throws UserNotFoundException, RoleNotFoundException;
    List<UserDtoGet> getUsers();
    UserEntity findUserById(Long id) throws UserNotFoundException;
    UserDtoGet findUserDtoById(Long id) throws UserNotFoundException;
    void deleteUser(Long id) throws UserNotFoundException;

}