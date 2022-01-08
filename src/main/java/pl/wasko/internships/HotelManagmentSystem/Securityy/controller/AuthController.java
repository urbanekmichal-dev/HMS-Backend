package pl.wasko.internships.HotelManagmentSystem.Securityy.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wasko.internships.HotelManagmentSystem.DTO.UserDTO.UserDtoGet;
import pl.wasko.internships.HotelManagmentSystem.DTO.UserDTO.UserDtoPost;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.BookingNotFoundException;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.RoleNotFoundException;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.UserNotFoundException;
import pl.wasko.internships.HotelManagmentSystem.Securityy.dto.*;
import pl.wasko.internships.HotelManagmentSystem.Securityy.service.AuthService;
import pl.wasko.internships.HotelManagmentSystem.Securityy.service.RefreshTokenService;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
        return new ResponseEntity<>("User Registration Successful",
                OK);
    }

    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Successfully", OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/refresh/token")
    public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully!!");
    }

    ////////////////////////////USER!!!!!!!!!!!!!!!!!

    @GetMapping(path = "/{username}")
    public ResponseEntity<UserResponse> getUserByUsername(@PathVariable("username") String username) throws BookingNotFoundException {
        return new ResponseEntity<>( authService.getUserByUsername(username), HttpStatus.OK);
    }

    @PutMapping(path = "/")
    public ResponseEntity<UserResponse> updateUserBody(
            @RequestBody RegisterRequest userDtoPost) throws UserNotFoundException {
        UserResponse updateUser = authService.updateUser(userDtoPost);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @GetMapping("/users/")
    public ResponseEntity<List<UserResponse>> getUsers() {
        return new ResponseEntity<>( authService.getUsers(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/user/{id}")
    public void deleteUser(@PathVariable("id") Long id) throws UserNotFoundException {
        authService.deleteUser(id);

    }
}
