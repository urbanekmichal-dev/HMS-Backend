package pl.wasko.internships.HotelManagmentSystem.Services.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wasko.internships.HotelManagmentSystem.DTO.RoleDTO.RoleDtoGet;
import pl.wasko.internships.HotelManagmentSystem.DTO.RoleDTO.RoleDtoPost;
import pl.wasko.internships.HotelManagmentSystem.Entities.*;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.RoleNotFoundException;
import pl.wasko.internships.HotelManagmentSystem.Mappers.RoleMapper;
import pl.wasko.internships.HotelManagmentSystem.Repositories.RoleRepository;
import pl.wasko.internships.HotelManagmentSystem.Services.RoleService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;


    @Transactional
    public RoleDtoGet addRole(RoleDtoPost roleDtoPost) {
        RoleEnity roleEnity=new RoleEnity();
        roleEnity.setTitle(roleDtoPost.getTitle());
        return roleMapper.roleToDTO(roleRepository.save(roleEnity));
    }

    public List<RoleDtoGet> getRoles() {
        return roleMapper.rolesWithUsersToDTO(roleRepository.findAll());
    }


    public RoleEnity findRoleById(Long id) throws RoleNotFoundException {
        return roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException(
                "Role with id " + id + " does not exist"));
    }

    @Transactional
    public void deleteRole(Long id) throws RoleNotFoundException {
        boolean exist = roleRepository.existsById(id);
        if (!exist) {
            throw new RoleNotFoundException("Role with id " + id + " does not exist ");

        }
        roleRepository.deleteById(id);
    }


    @Transactional
    public RoleDtoGet updateRole(RoleDtoPost roleDtoPost) throws RoleNotFoundException {
        RoleEnity roleEnityFind=roleRepository.findById(roleDtoPost.getId().longValue()).orElseThrow(() -> new RoleNotFoundException(
                "Role with id " + roleDtoPost.getId().longValue() + " does not exist!"
        ));

        if (roleDtoPost.getTitle()!= null &&
                roleDtoPost.getTitle().length() > 0 &&
                !Objects.equals((roleEnityFind.getTitle()), roleDtoPost.getTitle())) {
            roleEnityFind.setTitle(roleDtoPost.getTitle());
        }

        return roleMapper.roleToDTO(roleEnityFind);

    }



}
