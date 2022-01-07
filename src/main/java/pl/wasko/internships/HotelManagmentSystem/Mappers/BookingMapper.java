package pl.wasko.internships.HotelManagmentSystem.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.wasko.internships.HotelManagmentSystem.DTO.BookingDTO.BookingDtoGet;
import pl.wasko.internships.HotelManagmentSystem.DTO.RoomDTO.RoomDtoPost;
import pl.wasko.internships.HotelManagmentSystem.Entities.BookingEntity;
import pl.wasko.internships.HotelManagmentSystem.Entities.RoomEntity;
import pl.wasko.internships.HotelManagmentSystem.Entities.UserEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    List<BookingDtoGet> bookingsToDTO(List <BookingEntity> bookingEntities);
    BookingDtoGet bookingToDto(BookingEntity bookingEntity);

    default RoomDtoPost roomEntityToRoomDtoPost(RoomEntity roomEntity) {
        if ( roomEntity == null ) {
            return null;
        }

        RoomDtoPost roomDtoPost = new RoomDtoPost();

        roomDtoPost.setId( roomEntity.getId() );
        roomDtoPost.setRoomType( roomEntity.getRoomType().getValue() );
        roomDtoPost.setFloor( roomEntity.getFloor() );
        roomDtoPost.setPrice( roomEntity.getPrice() );
        roomDtoPost.setPicture( roomEntity.getPicture() );
        roomDtoPost.setAdults( roomEntity.getAdults() );
        roomDtoPost.setRoomsNumber( roomEntity.getRoomsNumber() );
        roomDtoPost.setChildren( roomEntity.getChildren() );
        roomDtoPost.setLocation( roomEntity.getLocation() );
        roomDtoPost.setDescription( roomEntity.getDescription() );

        return roomDtoPost;
    }
}
