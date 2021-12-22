package pl.wasko.internships.HotelManagmentSystem.DTO.BookingDTO;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDTOslim {
    @NotNull
    private Long id;

    @NotNull(message = "Check-in cannot be an empty value")
    private LocalDate checkIn;

    @NotNull(message = "Check-out cannot be an empty value")
    private LocalDate checkOut;


}
