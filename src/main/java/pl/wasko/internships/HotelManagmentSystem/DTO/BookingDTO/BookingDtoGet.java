package pl.wasko.internships.HotelManagmentSystem.DTO.BookingDTO;

import lombok.*;
import pl.wasko.internships.HotelManagmentSystem.DTO.PaymentDTO.PaymentDtoPost;
import pl.wasko.internships.HotelManagmentSystem.DTO.PaymentDTO.PaymentDtoSlim;
import pl.wasko.internships.HotelManagmentSystem.DTO.RoomDTO.RoomDtoPost;
import pl.wasko.internships.HotelManagmentSystem.DTO.UserDTO.UserDTOslim;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDtoGet {
    private Long id;

    @NotNull(message = "Check-in cannot be an empty value")
    private LocalDate checkIn;

    @NotNull(message = "Check-out cannot be an empty value")
    private LocalDate checkOut;

    private RoomDtoPost room;

    private UserDTOslim user;

    private PaymentDtoSlim payment;



}
