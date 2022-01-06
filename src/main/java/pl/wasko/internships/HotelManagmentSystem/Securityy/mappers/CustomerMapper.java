package pl.wasko.internships.HotelManagmentSystem.Securityy.mappers;
import org.mapstruct.Mapper;
import pl.wasko.internships.HotelManagmentSystem.Securityy.dto.UserResponse;
import pl.wasko.internships.HotelManagmentSystem.Securityy.model.User;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    UserResponse userToDTO(User user);
}

