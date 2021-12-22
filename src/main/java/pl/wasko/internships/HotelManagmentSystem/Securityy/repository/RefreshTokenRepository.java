package pl.wasko.internships.HotelManagmentSystem.Securityy.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.wasko.internships.HotelManagmentSystem.Securityy.model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    void deleteByToken(String token);
}
