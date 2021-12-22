package pl.wasko.internships.HotelManagmentSystem.DTO.PaymentDTO;

import lombok.*;
import pl.wasko.internships.HotelManagmentSystem.DTO.UserDTO.UserDTOslim;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDtoSlim {
    private Long id;
    @NotNull(message = "Amount cannot be an empty value")
    private Double amount;

    @NotNull(message = "Date cannot be an empty value")
    private String date;

    @NotNull(message = "Description cannot be an empty value")
    private String description;

}
