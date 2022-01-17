package pl.wasko.internships.HotelManagmentSystem.PagingSortingFiltration.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RoomSearchCriteria {
    private Integer floor;
    private Integer roomType;
    private Integer adults;
    private Integer roomsNumber;
    private Integer children;
    private String location;
    private String checkIn;
    private String checkOut;
    private Double priceFrom;
    private Double priceTo;


}
