package pl.wasko.internships.HotelManagmentSystem.Services.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import pl.wasko.internships.HotelManagmentSystem.DTO.BookingDTO.BookingDtoGet;
import pl.wasko.internships.HotelManagmentSystem.DTO.BookingDTO.BookingDtoPost;
import pl.wasko.internships.HotelManagmentSystem.Entities.PaymentEntity;
import pl.wasko.internships.HotelManagmentSystem.Entities.RoomEntity;
import pl.wasko.internships.HotelManagmentSystem.Entities.UserEntity;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.BookingNotFoundException;
import pl.wasko.internships.HotelManagmentSystem.Entities.BookingEntity;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.DateException;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.RoomNotFoundException;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.UserNotFoundException;
import pl.wasko.internships.HotelManagmentSystem.Mappers.BookingMapper;
import pl.wasko.internships.HotelManagmentSystem.Repositories.BookingRepository;
import org.springframework.stereotype.Service;
import pl.wasko.internships.HotelManagmentSystem.Repositories.RoomRepository;
import pl.wasko.internships.HotelManagmentSystem.Services.BookingService;
import pl.wasko.internships.HotelManagmentSystem.Services.RoomService;
import pl.wasko.internships.HotelManagmentSystem.Services.UserService;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final RoomService roomService;
    private final UserService userService;
    private final BookingMapper bookingMapper;


    public List<BookingDtoGet> getBookings() {
        return bookingMapper.bookingsToDTO(bookingRepository.findAll());
    }

    public List<BookingDtoGet> getBookingsByRoomId(Long id){
        return bookingMapper.bookingsToDTO(bookingRepository.findBookingsByRoomId(id));
    }

    public List<BookingDtoGet> getBookingsByUserId(Long id){
        return bookingMapper.bookingsToDTO(bookingRepository.findBookingsByUserId(id));
    }

    @Transactional
    public BookingDtoGet addBooking(BookingDtoPost bookingDtoPost) throws Exception {
        BookingEntity bookingEntity = new BookingEntity();
        PaymentEntity paymentEntity = new PaymentEntity();
        bookingEntity.setCheckIn(LocalDate.parse(bookingDtoPost.getCheckIn()));
        bookingEntity.setCheckOut(LocalDate.parse(bookingDtoPost.getCheckOut()));

        RoomEntity roomEntity=roomService.findRoomById(Long.parseLong(bookingDtoPost.getRoom()));
        UserEntity userEntity=userService.findUserById(Long.parseLong(bookingDtoPost.getUser()));

        paymentEntity.setDate(LocalDate.now());
        long days=daysBetween(bookingDtoPost.getCheckIn(),bookingDtoPost.getCheckOut());
        paymentEntity.setAmount(days*roomEntity.getPrice());
        paymentEntity.setDescription("dla testu tylko!");

        List <BookingEntity> entities= bookingRepository.findAll().
                stream()
                .filter(x -> x.getRoom().getId().equals(roomEntity.getId()))
                .collect(Collectors.toList());

        if(checkIfAvailable(bookingEntity,entities)) {

            bookingEntity.setUser(userEntity);
            bookingEntity.setRoom(roomEntity);
            bookingEntity.setPayment(paymentEntity);
            userEntity.getBookings().add(bookingEntity);
            userEntity.getPayments().add(paymentEntity);
            paymentEntity.setBooking(bookingEntity);
            paymentEntity.setUser(userEntity);
            roomEntity.getBookings().add(bookingEntity);
            return bookingMapper.bookingToDto(bookingRepository.save(bookingEntity));

        }
      else {
            throw new Exception("Room is reserved on the indicated date ");
        }
    }

    @Transactional
    public BookingDtoGet updateBooking(BookingDtoPost bookingDtoPost) throws BookingNotFoundException {
        BookingEntity bookingEntity = bookingRepository.findById(bookingDtoPost.getId()).orElseThrow(() -> new BookingNotFoundException(
                "Booking with id " + bookingDtoPost.getId() + " does not exist"));

        if (bookingDtoPost.getCheckIn() != null &&
                !Objects.equals(bookingDtoPost.getCheckIn(), bookingEntity.getCheckIn())) {
            bookingEntity.setCheckIn(LocalDate.parse(bookingDtoPost.getCheckIn()));
        }

        if (bookingDtoPost.getCheckOut() != null &&
                !Objects.equals(bookingEntity.getCheckOut(), bookingDtoPost.getCheckOut())) {
            bookingEntity.setCheckOut(LocalDate.parse(bookingDtoPost.getCheckOut()));
        }

        return bookingMapper.bookingToDto(bookingEntity) ;
    }


    @Transactional
    public void deleteBooking(Long booking_id) throws BookingNotFoundException {

        boolean exist = bookingRepository.existsById(booking_id);
        if(!exist){
            throw new BookingNotFoundException("Booking with id " + booking_id + " does not exist");
        }
        bookingRepository.deleteById(booking_id);
    }


    public BookingEntity findBookingById(Long id) throws BookingNotFoundException {
        return bookingRepository.findBookingById(id).orElseThrow(
                ()-> new BookingNotFoundException("Booking by id"+ id +"was not found"));
    }

    public long daysBetween(String inputString1,String inputString2) throws DateException {
        LocalDate date1=LocalDate.parse(inputString1);
        LocalDate date2=LocalDate.parse(inputString2);
        long days=Period.between(date1, date2).getDays();
        if(days<0) throw new DateException("Wrong date - negative number of days");
        return days;
    }

    public boolean checkIfAvailable(BookingEntity validEntity,List<BookingEntity> dbEntities){
        LocalDate vd1=  validEntity.getCheckIn();
        LocalDate vd2=validEntity.getCheckOut();

        for(BookingEntity be:dbEntities) {
            LocalDate dd1 = be.getCheckIn();
            LocalDate dd2 = be.getCheckOut();

            if(!(vd1.compareTo(dd2) >= 0 || dd1.compareTo(vd2) >= 0))
                 return false;

        }
        return true;

    }




}
