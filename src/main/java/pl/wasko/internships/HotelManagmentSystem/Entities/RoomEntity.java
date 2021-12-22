package pl.wasko.internships.HotelManagmentSystem.Entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Rooms")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;
    private String roomType;
    private Integer floor;
    private Double price;

    @JsonManagedReference
    @OneToMany(mappedBy="room",cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<BookingEntity> bookings = new ArrayList<>();




    public RoomEntity(String type, Integer floor, Double price) {
        this.roomType = type;
        this.floor = floor;
        this.price = price;
    }

}
