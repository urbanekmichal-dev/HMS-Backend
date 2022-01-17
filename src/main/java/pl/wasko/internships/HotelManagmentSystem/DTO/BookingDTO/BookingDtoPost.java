package pl.wasko.internships.HotelManagmentSystem.DTO.BookingDTO;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDtoPost {
    private Long id;

    private String checkIn;

    private String checkOut;

    private Integer room;

    private Integer user;

    private Integer owner;

}
