package pl.wasko.internships.HotelManagmentSystem.Repositories;

import org.springframework.data.jpa.repository.Query;
import pl.wasko.internships.HotelManagmentSystem.Entities.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository
        extends JpaRepository<BookingEntity, Long> {

    //@Query("Select b From Booking b where b.id = ?1")
    Optional <BookingEntity> findBookingById(Long booking_id);

    @Query("Select b From BookingEntity b where b.room.id = ?1")
    List <BookingEntity> findBookingsByRoomId(Long room);

    @Query("Select b From BookingEntity b where b.user.id = ?1")
    List <BookingEntity> findBookingsByUserId(Long room);


}


