package pl.wasko.internships.HotelManagmentSystem.Services;

import pl.wasko.internships.HotelManagmentSystem.DTO.BookingDTO.BookingDtoGet;
import pl.wasko.internships.HotelManagmentSystem.DTO.BookingDTO.BookingDtoPost;
import pl.wasko.internships.HotelManagmentSystem.Entities.BookingEntity;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.BookingNotFoundException;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.RoomNotFoundException;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.UserNotFoundException;

import java.text.ParseException;
import java.util.List;

public interface BookingService {
    BookingDtoGet addBooking(BookingDtoPost bookingDtoPost) throws Exception;
    BookingDtoGet updateBooking(BookingDtoPost bookingDtoPost) throws BookingNotFoundException;
    List<BookingDtoGet> getBookings();
    BookingEntity findBookingById(Long id) throws BookingNotFoundException;
    void deleteBooking(Long id) throws BookingNotFoundException;
    List<BookingDtoGet> getBookingsByRoomId(Long id);
    List<BookingDtoGet> getBookingsByUserId(Long id);
}
