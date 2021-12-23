package pl.wasko.internships.HotelManagmentSystem.DTO.RoomDTO;

import lombok.*;

import javax.validation.constraints.NotNull;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomDtoPost {
    private Long id;
    @NotNull(message = "Room type cannot be an empty value")
    private String roomType;

    @NotNull(message = "Floor cannot be an empty value")
    private Integer floor;

    @NotNull(message = "Price cannot be an empty value")
    private Double price;

    @NotNull(message = "Picture cannot be an empty value")
    private String picture;


}
