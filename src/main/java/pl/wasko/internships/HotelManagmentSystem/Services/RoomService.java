package pl.wasko.internships.HotelManagmentSystem.Services;

import org.springframework.data.domain.Page;
import pl.wasko.internships.HotelManagmentSystem.DTO.RoomDTO.RoomDtoGet;
import pl.wasko.internships.HotelManagmentSystem.DTO.RoomDTO.RoomDtoPost;
import pl.wasko.internships.HotelManagmentSystem.Entities.RoomEntity;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.RoomNotFoundException;
import pl.wasko.internships.HotelManagmentSystem.PagingSortingFiltration.model.RoomPage;
import pl.wasko.internships.HotelManagmentSystem.PagingSortingFiltration.model.RoomSearchCriteria;

import java.util.List;

public interface RoomService {
    RoomDtoGet addRoom(RoomDtoPost roomDtoPost);
    RoomDtoGet updateRoom(RoomDtoPost roomDtoPost) throws RoomNotFoundException;
    List<RoomDtoGet> getRooms();
    RoomEntity findRoomById(Long id) throws RoomNotFoundException;
    void deleteRoom(Long id) throws RoomNotFoundException;
    List<RoomDtoGet> getRooms(RoomPage roomPage, RoomSearchCriteria roomSearchCriteria);
}
