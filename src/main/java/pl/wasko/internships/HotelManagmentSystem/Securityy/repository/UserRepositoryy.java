package pl.wasko.internships.HotelManagmentSystem.Securityy.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wasko.internships.HotelManagmentSystem.Securityy.model.User;

import java.util.Optional;

@Repository
public interface UserRepositoryy extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}