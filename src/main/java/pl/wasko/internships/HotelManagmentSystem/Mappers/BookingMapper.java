package pl.wasko.internships.HotelManagmentSystem.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.wasko.internships.HotelManagmentSystem.DTO.BookingDTO.BookingDtoGet;
import pl.wasko.internships.HotelManagmentSystem.Entities.BookingEntity;
import pl.wasko.internships.HotelManagmentSystem.Entities.RoomEntity;
import pl.wasko.internships.HotelManagmentSystem.Entities.UserEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    List<BookingDtoGet> bookingsToDTO(List <BookingEntity> bookingEntities);
    BookingDtoGet bookingToDto(BookingEntity bookingEntity);
}
