package pl.wasko.internships.HotelManagmentSystem.Services.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.parameters.P;
import org.springframework.util.StringUtils;
import pl.wasko.internships.HotelManagmentSystem.DTO.BookingDTO.BookingDtoGet;
import pl.wasko.internships.HotelManagmentSystem.DTO.RoomDTO.RoomDtoGet;
import pl.wasko.internships.HotelManagmentSystem.DTO.RoomDTO.RoomDtoPost;
import pl.wasko.internships.HotelManagmentSystem.Entities.BookingEntity;
import pl.wasko.internships.HotelManagmentSystem.Entities.RoomType;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.BookingNotFoundException;
import pl.wasko.internships.HotelManagmentSystem.Entities.RoomEntity;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.RoomNotFoundException;
import pl.wasko.internships.HotelManagmentSystem.Mappers.BookingMapper;
import pl.wasko.internships.HotelManagmentSystem.Mappers.RoomMapper;
import pl.wasko.internships.HotelManagmentSystem.PagingSortingFiltration.Repository.RoomCriteriaRepository;
import pl.wasko.internships.HotelManagmentSystem.PagingSortingFiltration.model.RoomPage;
import pl.wasko.internships.HotelManagmentSystem.PagingSortingFiltration.model.RoomSearchCriteria;
import pl.wasko.internships.HotelManagmentSystem.Repositories.BookingRepository;
import pl.wasko.internships.HotelManagmentSystem.Repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils.*;
import org.springframework.stereotype.Service;
import pl.wasko.internships.HotelManagmentSystem.Securityy.model.Role;
import pl.wasko.internships.HotelManagmentSystem.Services.RoomService;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final BookingMapper bookingMapper;
    private final RoomCriteriaRepository roomCriteriaRepository;
    private final BookingRepository bookingRepository;


    public List<RoomDtoGet> getRooms() {
        return roomMapper.roomsToDTO(roomRepository.findAll());

    }
    /////////////////////////////////////////////////////////////////////
    public List<RoomDtoGet> getRooms(RoomPage roomPage, RoomSearchCriteria roomSearchCriteria)
    {
        Page<RoomEntity>  roomEntityPage= roomCriteriaRepository.findAllWithFilters(roomPage,roomSearchCriteria) ;
        List<RoomEntity> roomEntityList = roomEntityPage.getContent();
       List<RoomEntity> roomEntityListFinal =new ArrayList<>(roomEntityList);

        if(Objects.nonNull(roomSearchCriteria.getCheckIn()) && Objects.nonNull(roomSearchCriteria.getCheckOut()) )
        {
            List<LocalDate> days = new ArrayList<>(dateRangeToDateArray(LocalDate.parse(roomSearchCriteria.getCheckIn()), LocalDate.parse(roomSearchCriteria.getCheckOut())));
            List<BookingEntity> allBookings = bookingRepository.findAll();
            allBookings.forEach((BookingEntity bookingEntity)->{
                List<LocalDate> daysBooking = dateRangeToDateArray(bookingEntity.getCheckIn(), bookingEntity.getCheckOut());
                if(!notContains(days, daysBooking)) {
                    deleteRoomFromList(bookingEntity.getRoom().getId(),roomEntityListFinal);
                }
            });
        }


        return roomMapper.roomsToDTO(roomEntityListFinal);
    }
    public boolean notContains(List<LocalDate> a, List<LocalDate> b)
    {
       for(LocalDate s : a){
           if(b.contains(s)) return false;
       }
        return true;
    }

    public  void deleteRoomFromList(Long id, List<RoomEntity> roomEntities)
    {
        roomEntities.removeIf(room-> room.getId().equals(id));




    }



    public List<LocalDate> dateRangeToDateArray(LocalDate checkIn, LocalDate checkOut){
        List<LocalDate> blockedDays = new ArrayList<>();
        while(checkIn.compareTo(checkOut)<0)
        {
            blockedDays.add(checkIn);
            checkIn= checkIn.plusDays(1);
        }
        return blockedDays;

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
        roomEntity.setVisible(true);

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

    @Transactional
    public void setVisibleRoom(Long id) throws RoomNotFoundException {

       RoomEntity roomEntity = roomRepository.findById(id).orElseThrow(
               ()-> new RoomNotFoundException("Room with id "+ id +" does not exist")
       );

        roomEntity.setVisible(!roomEntity.getVisible());
    }



}
