package pl.wasko.internships.HotelManagmentSystem.Controllers;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.wasko.internships.HotelManagmentSystem.DTO.BookingDTO.BookingDtoGet;
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


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
    @PostMapping("/rooms")
    public ResponseEntity<List<RoomDtoGet>> getRooms(RoomPage roomPage, @RequestBody RoomSearchCriteria roomSearchCriteria) {
        return new ResponseEntity<>(roomService.getRooms(roomPage,roomSearchCriteria), HttpStatus.OK);
    }


    //////////////////////////////////////////////////////////////////////

    // define a location
    public static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads/";

    // Define a method to upload files
    @PostMapping("/upload")
    public ResponseEntity

            <Map<String, Object>> uploadFiles(@RequestParam("files")List<MultipartFile> multipartFiles) throws IOException {
        List<String> filenames = new ArrayList<>();
        for(MultipartFile file : multipartFiles) {
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
            copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
            filenames.add(filename);
        }
       // return ResponseEntity.ok().body(filenames);
        return ResponseEntity.ok().body(Map.of
                ("name",filenames,"k2","aaa"));
    }


    @GetMapping("download/{filename}")
    public ResponseEntity<Resource> downloadFiles(@PathVariable("filename") String filename) throws IOException {
        Path filePath = get(DIRECTORY).toAbsolutePath().normalize().resolve(filename);
        if(!Files.exists(filePath)) {
            throw new FileNotFoundException(filename + " was not found on the server");
        }
        Resource resource = new UrlResource(filePath.toUri());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("File-Name", filename);
        httpHeaders.add(CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
                .headers(httpHeaders).body(resource);
    }


    @DeleteMapping(path = "/visible/{id}")
    public ResponseEntity<RoomEntity> setVisibleRoom(
            @PathVariable("id") Long id) throws RoomNotFoundException {
        roomService.setVisibleRoom(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
