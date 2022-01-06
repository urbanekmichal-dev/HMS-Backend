package pl.wasko.internships.HotelManagmentSystem.Securityy.service;


import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.wasko.internships.HotelManagmentSystem.DTO.UserDTO.UserDtoGet;
import pl.wasko.internships.HotelManagmentSystem.DTO.UserDTO.UserDtoPost;
import pl.wasko.internships.HotelManagmentSystem.Entities.RoleEnity;
import pl.wasko.internships.HotelManagmentSystem.Entities.UserEntity;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.BookingNotFoundException;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.RoleNotFoundException;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.UserNotFoundException;
import pl.wasko.internships.HotelManagmentSystem.Securityy.dto.*;
import pl.wasko.internships.HotelManagmentSystem.Securityy.exceptions.SpringRedditException;
import pl.wasko.internships.HotelManagmentSystem.Securityy.mappers.CustomerMapper;
import pl.wasko.internships.HotelManagmentSystem.Securityy.model.NotificationEmail;
import pl.wasko.internships.HotelManagmentSystem.Securityy.model.Role;
import pl.wasko.internships.HotelManagmentSystem.Securityy.model.User;
import pl.wasko.internships.HotelManagmentSystem.Securityy.model.VerificationToken;

import pl.wasko.internships.HotelManagmentSystem.Securityy.repository.UserRepositoryy;
import pl.wasko.internships.HotelManagmentSystem.Securityy.repository.VerificationTokenRepository;
import pl.wasko.internships.HotelManagmentSystem.Securityy.security.JwtProvider;


import java.time.Instant;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepositoryy userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;
    private final CustomerMapper customerMapper;

    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());

        ///////////////////////////////Przełączyć na false żeby miałą sens weryfikacją1!!!!!1
        user.setEnabled(true);

        user.setFirstname(registerRequest.getFirstname());
        user.setLastname(registerRequest.getLastname());
        user.setPhone(registerRequest.getPhone());
        user.setAddress(registerRequest.getAddress());
        user.setRole(Role.values()[registerRequest.getRole()]);
        user.setImage(registerRequest.getImage());


        userRepository.save(user);

        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Please Activate your Account",
                user.getEmail(), "Thank you for signing up to HotelManagementSystem, " +
                "please click on the below url to activate your account : " +
                //"http://localhost:8083/api/auth/accountVerification/" + token));
                "http://hotelmanagementsystem-env.eba-mc24nyan.us-east-1.elasticbeanstalk.com/api/auth/accountVerification/"+ token));
    }

    @Transactional(readOnly = true)
    public User getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getUsername()));
    }

    private void fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new SpringRedditException("User not found with name - " + username));
        user.setEnabled(true);
        userRepository.save(user);
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        fetchUserAndEnable(verificationToken.orElseThrow(() -> new SpringRedditException("Invalid Token")));
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        User user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow(() -> new SpringRedditException("User not found with name - " + loginRequest.getUsername()));
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(loginRequest.getUsername())
                .userId((user.getUserId()))
                .build();
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUsername());
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(refreshTokenRequest.getUsername())
                .build();
    }

    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }

    public UserResponse getUserByUsername(String username) throws BookingNotFoundException {
        return customerMapper.userToDTO(userRepository.findByUsername(username).orElseThrow(
                ()-> new BookingNotFoundException("User with username: "+ username +"was not found")));
    }

    public UserResponse updateUser(UserResponse userResponse) throws UserNotFoundException {
        User userFind=userRepository.findByUsername(userResponse.getUsername()) .orElseThrow(() -> new UserNotFoundException(
                "User with username " + userResponse.getUsername() + " does not exist!"
        ));

        if (userResponse.getFirstname()!= null &&
                userResponse.getFirstname().length() > 0 &&
                !Objects.equals((userFind.getFirstname()), userResponse.getFirstname())) {
            userFind.setFirstname(userResponse.getFirstname());
        }

        if (userResponse.getLastname()!= null &&
                userResponse.getLastname().length() > 0 &&
                !Objects.equals((userFind.getLastname()), userResponse.getLastname())) {
            userFind.setLastname(userResponse.getLastname());
        }

        if (userResponse.getPhone()!= null &&
                userResponse.getPhone().length() > 0 &&
                !Objects.equals((userFind.getPhone()), userResponse.getPhone())) {
            userFind.setPhone(userResponse.getPhone());
        }

        if (userResponse.getAddress()!= null &&
                userResponse.getAddress().length() > 0 &&
                !Objects.equals((userFind.getAddress()), userResponse.getAddress())) {
            userFind.setAddress(userResponse.getAddress());
        }

        if (userResponse.getEmail()!= null &&
                userResponse.getEmail().length() > 0 &&
                !Objects.equals((userFind.getEmail()), userResponse.getEmail())) {
            userFind.setEmail(userResponse.getEmail());
        }

        return customerMapper.userToDTO(userFind);
    }


}