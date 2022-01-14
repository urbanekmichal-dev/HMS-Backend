package pl.wasko.internships.HotelManagmentSystem.PagingSortingFiltration.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RoomSearchCriteria {
    private Integer floor;
    private String roomType;
    private Double price;
    private Integer adults;
    private Integer roomsNumber;
    private Integer children;
    private String location;
    private String checkIn;
    private String checkOut;
}
