package pl.wasko.internships.HotelManagmentSystem.Controllers;

import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.wasko.internships.HotelManagmentSystem.DTO.BookingDTO.BookingDtoGet;
import pl.wasko.internships.HotelManagmentSystem.DTO.BookingDTO.BookingDtoPost;
import pl.wasko.internships.HotelManagmentSystem.Entities.BookingEntity;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.BookingNotFoundException;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.DateException;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.RoomNotFoundException;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.UserNotFoundException;
import pl.wasko.internships.HotelManagmentSystem.Securityy.exceptions.ApiRequestException;
import pl.wasko.internships.HotelManagmentSystem.Services.BookingService;
import pl.wasko.internships.HotelManagmentSystem.Services.impl.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/")
    public ResponseEntity<List<BookingDtoGet>> getBookings(){
        return new ResponseEntity<>(bookingService.getBookings(), HttpStatus.OK);
    }

    @GetMapping("/room/{id}")
    public ResponseEntity<List<BookingDtoGet>> getBookingsByRoomId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(bookingService.getBookingsByRoomId(id), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<BookingDtoGet>> getBookingsByUserId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(bookingService.getBookingsByUserId(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<BookingDtoGet> addBooking(@RequestBody BookingDtoPost bookingDtoPost) throws Exception {
        BookingDtoGet newBookingEntity = bookingService.addBooking(bookingDtoPost);
        return new ResponseEntity<>(newBookingEntity, HttpStatus.CREATED);
    }


    @PutMapping("/")
    public ResponseEntity<BookingDtoGet> updateBooking(@RequestBody BookingDtoPost bookingDtoPost) throws BookingNotFoundException {
        BookingDtoGet updateBookingEntity = bookingService.updateBooking(bookingDtoPost);
        return new ResponseEntity<>(updateBookingEntity, HttpStatus.OK);

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BookingEntity> deleteBoooking(@PathVariable("id") Long id) throws BookingNotFoundException {
        bookingService.deleteBooking(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/checkincheckout/{id}")
    public ResponseEntity<List<LocalDate>> getCheckInCheckOutDays(@PathVariable("id") Long id){
        return new ResponseEntity<>(bookingService.getCheckInCheckOutDays(id), HttpStatus.OK);
    }


}
