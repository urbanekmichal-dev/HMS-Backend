package pl.wasko.internships.HotelManagmentSystem.Services;

import pl.wasko.internships.HotelManagmentSystem.DTO.RoleDTO.RoleDtoGet;
import pl.wasko.internships.HotelManagmentSystem.DTO.RoleDTO.RoleDtoPost;
import pl.wasko.internships.HotelManagmentSystem.Entities.RoleEnity;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.RoleNotFoundException;

import java.util.List;

public interface RoleService {
    RoleDtoGet addRole(RoleDtoPost roleDtoPost);
    RoleDtoGet updateRole(RoleDtoPost roleDtoPost) throws RoleNotFoundException;
    List<RoleDtoGet> getRoles();
    RoleEnity findRoleById(Long id) throws RoleNotFoundException;
    void deleteRole(Long id) throws RoleNotFoundException;
}
