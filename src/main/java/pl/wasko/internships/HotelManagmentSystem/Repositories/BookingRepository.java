package pl.wasko.internships.HotelManagmentSystem.Repositories;

import org.springframework.data.jpa.repository.Query;
import pl.wasko.internships.HotelManagmentSystem.Entities.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository
        extends JpaRepository<BookingEntity, Long> {

    //@Query("Select b From Booking b where b.id = ?1")
    Optional <BookingEntity> findBookingById(Long booking_id);


}


