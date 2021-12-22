package pl.wasko.internships.HotelManagmentSystem.Services;

import pl.wasko.internships.HotelManagmentSystem.DTO.PaymentDTO.PaymentDtoGet;
import pl.wasko.internships.HotelManagmentSystem.DTO.PaymentDTO.PaymentDtoPost;
import pl.wasko.internships.HotelManagmentSystem.Entities.PaymentEntity;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.PaymentNotFoundException;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.UserNotFoundException;

import java.util.List;

public interface PaymentService {
    PaymentDtoGet addPayment(PaymentDtoPost paymentDtoPost) throws UserNotFoundException;
    PaymentDtoGet updatePayment(PaymentDtoPost paymentDtoPost) throws PaymentNotFoundException;
    List<PaymentDtoGet> getPayments();
    PaymentEntity findPaymentById(Long id) throws PaymentNotFoundException;
    void deletePayment(Long id) throws PaymentNotFoundException;

}
