package pl.wasko.internships.HotelManagmentSystem.Services.impl;

import lombok.AllArgsConstructor;
import pl.wasko.internships.HotelManagmentSystem.DTO.RoomDTO.RoomDtoGet;
import pl.wasko.internships.HotelManagmentSystem.DTO.RoomDTO.RoomDtoPost;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.BookingNotFoundException;
import pl.wasko.internships.HotelManagmentSystem.Entities.RoomEntity;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.RoomNotFoundException;
import pl.wasko.internships.HotelManagmentSystem.Mappers.RoomMapper;
import pl.wasko.internships.HotelManagmentSystem.Repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wasko.internships.HotelManagmentSystem.Services.RoomService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;


    public List<RoomDtoGet> getRooms() {
        return roomMapper.roomsToDTO(roomRepository.findAll());

    }

    @Transactional
    public RoomDtoGet addRoom(RoomDtoPost roomDtoPost) {
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setRoomType(roomDtoPost.getRoomType());
        roomEntity.setFloor(roomDtoPost.getFloor());
        roomEntity.setPrice(roomDtoPost.getPrice());
        roomEntity.setPicture(roomDtoPost.getPicture());

        return roomMapper.roomToDto(roomRepository.save(roomEntity));

    }


    @Transactional
    public RoomDtoGet updateRoom(RoomDtoPost roomDtoPost) throws RoomNotFoundException {
        RoomEntity roomEntity = roomRepository.findById(roomDtoPost.getId()).orElseThrow(
                () -> new RoomNotFoundException(
                        "Room with id " + roomDtoPost.getId() + " does not exist")

        );
        if (roomDtoPost.getRoomType() != null &&
                !Objects.equals(roomDtoPost.getRoomType(), roomEntity.getRoomType())) {
            roomEntity.setRoomType(roomDtoPost.getRoomType());
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

//    @Transactional
//    public void upadateRoom(Long room_id,
//                            String type,
//                            Integer floor,
//                            Double price) {
//        RoomEntity room = roomRepository.findById(room_id)
//                .orElseThrow(() -> new IllegalStateException(
//                        "room with id " + room_id + " does not exist!"
//                ));
//
//
//        if (floor != null &&
//
//                !Objects.equals((room.getFloor()), floor)) {
//            room.setFloor(floor);
//        }
//
//        if (price != null
//                && !Objects.equals(room.getPrice(), price)) {
//            room.setPrice(price);
//        }
//
//    }
}
