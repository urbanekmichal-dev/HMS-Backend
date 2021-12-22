package pl.wasko.internships.HotelManagmentSystem.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.wasko.internships.HotelManagmentSystem.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    //@Query("Select u From User u where u.id =?1 ")
    Optional<UserEntity> findUserById(Long user_id);

    @Query("FROM UserEntity WHERE login=:login")
    UserEntity findByLogin(@Param("login") String login);
}



