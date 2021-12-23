package pl.wasko.internships.HotelManagmentSystem.DTO.RoomDTO;

import javax.validation.constraints.NotNull;

import lombok.*;
import pl.wasko.internships.HotelManagmentSystem.DTO.BookingDTO.BookingDTOslim;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomDtoGet {
    private Long id;
    @NotNull(message = "Room type cannot be an empty value")
    private String roomType;

    @NotNull(message = "Floor cannot be an empty value")
    private Integer floor;

    @NotNull(message = "Price cannot be an empty value")
    private Double price;

    @NotNull(message = "Picture cannot be an empty value")
    private String picture;

    private List<BookingDTOslim> bookings;


}
