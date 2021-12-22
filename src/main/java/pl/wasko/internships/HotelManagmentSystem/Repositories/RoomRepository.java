package pl.wasko.internships.HotelManagmentSystem.Repositories;

import pl.wasko.internships.HotelManagmentSystem.Entities.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository
        extends JpaRepository<RoomEntity, Long> {

    //@Query("Select r From Room r where r.id =?1 ")
    //Optional<Room> findRoomById(Long room_id);
}
