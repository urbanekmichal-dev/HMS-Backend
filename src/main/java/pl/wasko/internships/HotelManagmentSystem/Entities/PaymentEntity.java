package pl.wasko.internships.HotelManagmentSystem.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "Payments")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payments")
    private Long id;
    private Double amount;
    private LocalDate date;
    private String description;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @JsonManagedReference
    @OneToOne(mappedBy="payment",cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private BookingEntity booking;


    public PaymentEntity(Double amount, LocalDate date, String description) {
        this.amount = amount;
        this.date = date;
        this.description = description;
    }
}
