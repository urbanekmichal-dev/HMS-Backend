package pl.wasko.internships.HotelManagmentSystem.Entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import pl.wasko.internships.HotelManagmentSystem.Securityy.model.User;

import javax.persistence.*;
import java.time.LocalDate;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Bookings")
@Entity
public class BookingEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "id_booking")
    private Long id;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate checkIn;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate checkOut;

    @ToString.Exclude
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "room_id", nullable = false)
    private RoomEntity room;

    @ToString.Exclude
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ToString.Exclude
    @JsonManagedReference
    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", nullable = false)
    private PaymentEntity payment;

    @ToString.Exclude
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;



    public BookingEntity(LocalDate checkIn, LocalDate checkOut) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
}


