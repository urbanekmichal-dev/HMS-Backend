package pl.wasko.internships.HotelManagmentSystem.Securityy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.wasko.internships.HotelManagmentSystem.Securityy.model.Role;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {
    private String authenticationToken;
    private String refreshToken;
    private Instant expiresAt;
    private String username;
    private Long userId;
    private Role role;
}
