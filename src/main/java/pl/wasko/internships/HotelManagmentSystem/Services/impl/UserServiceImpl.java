package pl.wasko.internships.HotelManagmentSystem.Services.impl;

import lombok.AllArgsConstructor;
import pl.wasko.internships.HotelManagmentSystem.DTO.BookingDTO.BookingDtoGet;
import pl.wasko.internships.HotelManagmentSystem.DTO.RoomDTO.RoomDtoGet;
import pl.wasko.internships.HotelManagmentSystem.DTO.UserDTO.UserDtoGet;
import pl.wasko.internships.HotelManagmentSystem.DTO.UserDTO.UserDtoPost;
import pl.wasko.internships.HotelManagmentSystem.Entities.BookingEntity;
import pl.wasko.internships.HotelManagmentSystem.Entities.RoleEnity;
import pl.wasko.internships.HotelManagmentSystem.Entities.RoomEntity;
import pl.wasko.internships.HotelManagmentSystem.Entities.UserEntity;

import pl.wasko.internships.HotelManagmentSystem.Exceptions.RoleNotFoundException;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.UserNotFoundException;
import pl.wasko.internships.HotelManagmentSystem.Mappers.BookingMapper;
import pl.wasko.internships.HotelManagmentSystem.Mappers.UserMapper;
import pl.wasko.internships.HotelManagmentSystem.Repositories.BookingRepository;
import pl.wasko.internships.HotelManagmentSystem.Repositories.UserRepository;
import org.springframework.stereotype.Service;
import pl.wasko.internships.HotelManagmentSystem.Services.BookingService;
import pl.wasko.internships.HotelManagmentSystem.Services.RoleService;
import pl.wasko.internships.HotelManagmentSystem.Services.RoomService;
import pl.wasko.internships.HotelManagmentSystem.Services.UserService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleService roleService;





    @Transactional
    public UserDtoGet addUser(UserDtoPost userDTO) throws RoleNotFoundException {

        UserEntity user = new UserEntity(
                userDTO.getName(),
                userDTO.getLastName(),
                userDTO.getPhone(),
                userDTO.getLogin(),
                userDTO.getPassword());

        RoleEnity roleEnity = roleService.findRoleById(Long.parseLong(userDTO.getRole()));
        user.setRole(roleEnity);
        roleEnity.getUsers().add(user);

        return userMapper.userToDTO(userRepository.save(user)) ;
    }


    public List<UserDtoGet> getUsers() {
        return userMapper.usersToDTO(userRepository.findAll());
    }




    public UserEntity findUserById(Long id) throws UserNotFoundException {

        return userRepository.findUserById(id).orElseThrow(() -> new UserNotFoundException(
                "User with id " + id + " does not exist")) ;
    }

    @Override
    public UserDtoGet findUserDtoById(Long id) throws UserNotFoundException {
        UserEntity userEntity=findUserById(id);
        return userMapper.userToDTO(userEntity);
    }



    @Transactional
    public void deleteUser(Long id) throws UserNotFoundException {
        boolean exist = userRepository.existsById(id);
        if (!exist) {
            throw new UserNotFoundException(
                    "User with id " + id + " does not exist "
            );
        }
        userRepository.deleteById(id);
    }


    @Transactional
    public UserDtoGet updateUser(UserDtoPost userDtoPost) throws UserNotFoundException, RoleNotFoundException {
        UserEntity userEntityFind = userRepository.findById(userDtoPost.getId().longValue())
                .orElseThrow(() -> new UserNotFoundException(
                        "User with id " + userDtoPost.getId().longValue() + " does not exist!"
                ));

        if (userDtoPost.getName()!= null &&
                userDtoPost.getName().length() > 0 &&
                !Objects.equals((userEntityFind.getName()), userDtoPost.getName())) {
            userEntityFind.setName(userDtoPost.getName());
        }

        if (userDtoPost.getLastName()!= null &&
                userDtoPost.getLastName().length() > 0 &&
                !Objects.equals((userEntityFind.getLastName()), userDtoPost.getLastName())) {
            userEntityFind.setLastName(userDtoPost.getLastName());
        }

        if (userDtoPost.getPhone()!= null &&
                userDtoPost.getPhone().length() > 0 &&
                !Objects.equals((userEntityFind.getPhone()), userDtoPost.getPhone())) {
            userEntityFind.setPhone(userDtoPost.getPhone());
        }

        if (userDtoPost.getLogin()!= null &&
                userDtoPost.getLogin().length() > 0 &&
                !Objects.equals((userEntityFind.getLogin()), userDtoPost.getLogin())) {
            userEntityFind.setLogin(userDtoPost.getLogin());
        }



        if (userDtoPost.getPassword()!= null &&
                userDtoPost.getPassword().length() > 0 &&
                !Objects.equals((userEntityFind.getPassword()), userDtoPost.getPassword())) {
            userEntityFind.setPassword(userDtoPost.getPassword());
        }

        RoleEnity roleEnity = roleService.findRoleById(Long.parseLong(userDtoPost.getRole()));
        userEntityFind.setRole(roleEnity);
        roleEnity.getUsers().add(userEntityFind);



        return userMapper.userToDTO(userEntityFind);
    }


}
