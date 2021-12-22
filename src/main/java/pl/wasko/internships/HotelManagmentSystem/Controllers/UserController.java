package pl.wasko.internships.HotelManagmentSystem.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.wasko.internships.HotelManagmentSystem.DTO.BookingDTO.BookingDtoGet;
import pl.wasko.internships.HotelManagmentSystem.DTO.UserDTO.UserDtoGet;
import pl.wasko.internships.HotelManagmentSystem.DTO.UserDTO.UserDtoPost;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.RoleNotFoundException;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.UserNotFoundException;
import pl.wasko.internships.HotelManagmentSystem.Services.UserService;
import pl.wasko.internships.HotelManagmentSystem.Services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDtoGet>> getUsers() {
        return new ResponseEntity<>( userService.getUsers(), HttpStatus.OK);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDtoGet> getBookingsByUserId(@PathVariable("id") Long id) throws UserNotFoundException {
        return new ResponseEntity<>( userService.findUserDtoById(id), HttpStatus.OK);
    }

    @PostMapping("/")
  public ResponseEntity<UserDtoGet> addUser(@RequestBody UserDtoPost userDtoPost) throws RoleNotFoundException {
       return new ResponseEntity<>(userService.addUser(userDtoPost), HttpStatus.CREATED);
   }

    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable("id") Long id
            ) throws UserNotFoundException {
        userService.deleteUser(id);

    }

    @PutMapping(path = "/")
    public ResponseEntity<UserDtoGet> updateUserBody(
            @RequestBody UserDtoPost userDtoPost) throws UserNotFoundException, RoleNotFoundException {
        UserDtoGet updateUser = userService.updateUser(userDtoPost);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);

    }




}