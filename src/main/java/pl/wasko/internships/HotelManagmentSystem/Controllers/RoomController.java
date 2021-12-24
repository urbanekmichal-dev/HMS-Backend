package pl.wasko.internships.HotelManagmentSystem.Controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.wasko.internships.HotelManagmentSystem.DTO.RoomDTO.RoomDtoGet;
import pl.wasko.internships.HotelManagmentSystem.DTO.RoomDTO.RoomDtoPost;
import pl.wasko.internships.HotelManagmentSystem.Entities.RoomEntity;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.RoomNotFoundException;
import pl.wasko.internships.HotelManagmentSystem.PagingSortingFiltration.model.RoomPage;
import pl.wasko.internships.HotelManagmentSystem.PagingSortingFiltration.model.RoomSearchCriteria;
import pl.wasko.internships.HotelManagmentSystem.Services.RoomService;
import pl.wasko.internships.HotelManagmentSystem.Services.impl.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/rooms")
public class RoomController {
    private final RoomService roomService;


    @Autowired
    public RoomController(RoomServiceImpl roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/")
    public ResponseEntity<List<RoomDtoGet>> getRooms() {
        List<RoomDtoGet> rooms = roomService.getRooms();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<RoomDtoGet> addRoom(@RequestBody RoomDtoPost roomDtoPost) {
        return new ResponseEntity<>(roomService.addRoom(roomDtoPost), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<RoomEntity> deleteRoom(
            @PathVariable("id") Long id) throws RoomNotFoundException {
        roomService.deleteRoom(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<RoomDtoGet> updateRoom(
            @RequestBody RoomDtoPost roomDtoPost) throws RoomNotFoundException {
        RoomDtoGet updateRoomEntity = roomService.updateRoom(roomDtoPost);
        return new ResponseEntity<>(updateRoomEntity,HttpStatus.OK);

    }

    //////////////////////////////////////////////////////////////////////
    @GetMapping("/rooms")
    public ResponseEntity<List<RoomDtoGet>> getRooms(RoomPage roomPage, RoomSearchCriteria roomSearchCriteria) {
        return new ResponseEntity<>(roomService.getRooms(roomPage,roomSearchCriteria), HttpStatus.OK);
    }


    //////////////////////////////////////////////////////////////////////

}
