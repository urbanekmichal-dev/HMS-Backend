package pl.wasko.internships.HotelManagmentSystem.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wasko.internships.HotelManagmentSystem.Entities.RoleEnity;

public interface RoleRepository extends JpaRepository<RoleEnity,Long> {
}
