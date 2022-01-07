package pl.wasko.internships.HotelManagmentSystem.Services.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import pl.wasko.internships.HotelManagmentSystem.DTO.RoomDTO.RoomDtoGet;
import pl.wasko.internships.HotelManagmentSystem.DTO.RoomDTO.RoomDtoPost;
import pl.wasko.internships.HotelManagmentSystem.Entities.RoomType;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.BookingNotFoundException;
import pl.wasko.internships.HotelManagmentSystem.Entities.RoomEntity;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.RoomNotFoundException;
import pl.wasko.internships.HotelManagmentSystem.Mappers.RoomMapper;
import pl.wasko.internships.HotelManagmentSystem.PagingSortingFiltration.Repository.RoomCriteriaRepository;
import pl.wasko.internships.HotelManagmentSystem.PagingSortingFiltration.model.RoomPage;
import pl.wasko.internships.HotelManagmentSystem.PagingSortingFiltration.model.RoomSearchCriteria;
import pl.wasko.internships.HotelManagmentSystem.Repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils.*;
import org.springframework.stereotype.Service;
import pl.wasko.internships.HotelManagmentSystem.Securityy.model.Role;
import pl.wasko.internships.HotelManagmentSystem.Services.RoomService;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final RoomCriteriaRepository roomCriteriaRepository;


    public List<RoomDtoGet> getRooms() {
        return roomMapper.roomsToDTO(roomRepository.findAll());

    }
    /////////////////////////////////////////////////////////////////////
    public List<RoomDtoGet> getRooms(RoomPage roomPage, RoomSearchCriteria roomSearchCriteria)
    {
        Page<RoomEntity>  roomEntityPage= roomCriteriaRepository.findAllWithFilters(roomPage,roomSearchCriteria) ;
        List<RoomEntity> roomEntityList = roomEntityPage.getContent();
        return roomMapper.roomsToDTO(roomEntityList);
    }

    public RoomEntity addRoom(RoomEntity roomEntity){


        return roomRepository.save(roomEntity);
    }


    /////////////////////////////////////////////////////////////////////

    @Transactional
    public RoomDtoGet addRoom(RoomDtoPost roomDtoPost) {
        RoomEntity roomEntity = new RoomEntity();

        roomEntity.setRoomType(RoomType.values()[roomDtoPost.getRoomType()] );
        roomEntity.setFloor(roomDtoPost.getFloor());
        roomEntity.setPrice(roomDtoPost.getPrice());
        roomEntity.setPicture(roomDtoPost.getPicture());
        roomEntity.setAdults(roomDtoPost.getAdults());
        roomEntity.setRoomsNumber(roomDtoPost.getRoomsNumber());
        roomEntity.setChildren(roomDtoPost.getChildren());
        roomEntity.setLocation(roomDtoPost.getLocation());
        roomEntity.setDescription(roomDtoPost.getDescription());

        return roomMapper.roomToDto(roomRepository.save(roomEntity));

    }


    @Transactional
    public RoomDtoGet updateRoom(RoomDtoPost roomDtoPost) throws RoomNotFoundException {
        RoomEntity roomEntity = roomRepository.findById(roomDtoPost.getId()).orElseThrow(
                () -> new RoomNotFoundException(
                        "Room with id " + roomDtoPost.getId() + " does not exist")

        );
        if (roomDtoPost.getRoomType() != null &&
                !Objects.equals(RoomType.values()[roomDtoPost.getRoomType()], roomEntity.getRoomType())) {
            roomEntity.setRoomType(RoomType.values()[roomDtoPost.getRoomType()]);
        }

        if (roomDtoPost.getFloor() != null &&
                !Objects.equals(roomDtoPost.getFloor(), roomEntity.getFloor())) {
            roomEntity.setFloor(roomDtoPost.getFloor());
        }

        if (roomDtoPost.getPrice() != null &&
                !Objects.equals(roomDtoPost.getPrice(), roomEntity.getPrice())) {
            roomEntity.setPrice(roomDtoPost.getPrice());
        }

        return roomMapper.roomToDto(roomEntity);
    }


    @Transactional
    public RoomEntity findRoomById(Long id) throws RoomNotFoundException {
        return roomRepository.findById(id).orElseThrow(
                ()-> new RoomNotFoundException("Room with id "+ id +" does not exist")
        );
    }

//    @Transactional
//    public void addNewRoom(RoomEntity room) {
//        Optional<RoomEntity> roomById = roomRepository.
//                findById(room.getId());
//        if (roomById.isPresent()) {
//            throw new IllegalStateException("id taken");
//        }
//        roomRepository.save(room);
//        System.out.println(room);
//    }

    @Transactional
    public void deleteRoom(Long id) throws RoomNotFoundException {
        boolean exist = roomRepository.existsById(id);
        if (!exist) {
            throw new RoomNotFoundException(
                    "Room with id " + id + " does not exist "
            );
        }
        roomRepository.deleteById(id);
    }



}
