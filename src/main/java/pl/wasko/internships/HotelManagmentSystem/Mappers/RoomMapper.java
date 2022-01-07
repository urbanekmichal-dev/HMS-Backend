package pl.wasko.internships.HotelManagmentSystem.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import pl.wasko.internships.HotelManagmentSystem.DTO.RoomDTO.RoomDtoGet;

import pl.wasko.internships.HotelManagmentSystem.DTO.RoomDTO.RoomDtoPost;
import pl.wasko.internships.HotelManagmentSystem.Entities.RoomEntity;

import java.util.ArrayList;
import java.util.List;


@Mapper(componentModel = "spring")
public interface RoomMapper {
    List<RoomDtoGet> roomsToDTO(List <RoomEntity> rooms);
     RoomDtoGet roomToDto(RoomEntity roomEntity);
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
