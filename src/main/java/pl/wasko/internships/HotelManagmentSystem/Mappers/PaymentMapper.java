package pl.wasko.internships.HotelManagmentSystem.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.wasko.internships.HotelManagmentSystem.DTO.PaymentDTO.PaymentDtoGet;
import pl.wasko.internships.HotelManagmentSystem.Entities.PaymentEntity;
import pl.wasko.internships.HotelManagmentSystem.Entities.UserEntity;

import java.util.List;


@Mapper(componentModel = "spring")
public interface PaymentMapper {
    List<PaymentDtoGet> paymentsToDTO(List <PaymentEntity> payments);
    PaymentDtoGet paymentToDo(PaymentEntity paymentEntity);
}
