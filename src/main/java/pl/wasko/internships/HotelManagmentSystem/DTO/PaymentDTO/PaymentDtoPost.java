package pl.wasko.internships.HotelManagmentSystem.DTO.PaymentDTO;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDtoPost {
    private Long id;
    @NotNull(message = "Amount cannot be an empty value")
    private Double amount;

    @NotNull(message = "Date cannot be an empty value")
    private String date;

    @NotNull(message = "Description cannot be an empty value")
    private String description;

    private String user;

}
