package pl.wasko.internships.HotelManagmentSystem.Entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    private RoomType roomType;
    private Integer floor;
    private Double price;
    private String picture;
    private Integer adults;
    private Integer roomsNumber;
    private Integer children;
    private String location;
    private String description;

    @JsonManagedReference
    @OneToMany(mappedBy="room",cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<BookingEntity> bookings = new ArrayList<>();


    public RoomEntity(RoomType roomType, Integer floor, Double price, String picture,
                      Integer adults, Integer roomsNumber, Integer children,
                      String location, String description) {
        this.roomType = roomType;
        this.floor = floor;
        this.price = price;
        this.picture = picture;
        this.adults = adults;
        this.roomsNumber = roomsNumber;
        this.children = children;
        this.location = location;
        this.description = description;
    }
}
