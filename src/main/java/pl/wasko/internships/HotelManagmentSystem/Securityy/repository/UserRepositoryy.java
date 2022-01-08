package pl.wasko.internships.HotelManagmentSystem.Securityy.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.wasko.internships.HotelManagmentSystem.Entities.BookingEntity;
import pl.wasko.internships.HotelManagmentSystem.Entities.UserEntity;
import pl.wasko.internships.HotelManagmentSystem.Securityy.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepositoryy extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("Select u From User u where u.userId = ?1")
    Optional<User> findUserById(Long userId);

}
