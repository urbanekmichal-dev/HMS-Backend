package pl.wasko.internships.HotelManagmentSystem.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import pl.wasko.internships.HotelManagmentSystem.DTO.RoomDTO.RoomDtoGet;

import pl.wasko.internships.HotelManagmentSystem.Entities.RoomEntity;

import java.util.ArrayList;
import java.util.List;


@Mapper(componentModel = "spring")
public interface RoomMapper {
    List<RoomDtoGet> roomsToDTO(List <RoomEntity> rooms);
    RoomDtoGet roomToDto(RoomEntity roomEntity);


}
