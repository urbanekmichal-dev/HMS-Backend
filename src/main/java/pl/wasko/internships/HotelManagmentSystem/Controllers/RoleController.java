package pl.wasko.internships.HotelManagmentSystem.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wasko.internships.HotelManagmentSystem.DTO.RoleDTO.RoleDtoGet;
import pl.wasko.internships.HotelManagmentSystem.DTO.RoleDTO.RoleDtoPost;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.RoleNotFoundException;
import pl.wasko.internships.HotelManagmentSystem.Services.RoleService;
import pl.wasko.internships.HotelManagmentSystem.Services.impl.RoleServiceImpl;

import java.util.List;

@RestController
@RequestMapping(path = "roles")
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController( RoleService roleService) {
        this.roleService = roleService;

    }


    @GetMapping("/")
    public ResponseEntity<List<RoleDtoGet>> getRoles() {
        return new ResponseEntity<>(roleService.getRoles(), HttpStatus.OK);
    }

    @PostMapping("/")
        public ResponseEntity<RoleDtoGet> addRole(@RequestBody RoleDtoPost roleDtoPost) {
            return new ResponseEntity<>(roleService.addRole(roleDtoPost), HttpStatus.CREATED);

    }

    @PutMapping("/")
    public ResponseEntity<RoleDtoGet> updateRole(@RequestBody RoleDtoPost roleDtoPost) throws RoleNotFoundException {
        RoleDtoGet updateRole = roleService.updateRole(roleDtoPost);
        return new ResponseEntity<>(updateRole,HttpStatus.OK);
    }

    @DeleteMapping("/")
    public void deleteRole(@RequestBody RoleDtoPost roleDtoPost) throws RoleNotFoundException {
        roleService.deleteRole(roleDtoPost.getId().longValue());
    }


}
