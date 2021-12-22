package pl.wasko.internships.HotelManagmentSystem.Services.impl;
import lombok.AllArgsConstructor;
import pl.wasko.internships.HotelManagmentSystem.DTO.PaymentDTO.PaymentDtoGet;
import pl.wasko.internships.HotelManagmentSystem.DTO.PaymentDTO.PaymentDtoPost;
import pl.wasko.internships.HotelManagmentSystem.Entities.UserEntity;
import pl.wasko.internships.HotelManagmentSystem.Entities.PaymentEntity;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.PaymentNotFoundException;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.UserNotFoundException;
import pl.wasko.internships.HotelManagmentSystem.Mappers.PaymentMapper;
import pl.wasko.internships.HotelManagmentSystem.Repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wasko.internships.HotelManagmentSystem.Services.PaymentService;
import pl.wasko.internships.HotelManagmentSystem.Services.UserService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserService userService;
    private final PaymentMapper paymentMapper;

    public List<PaymentDtoGet> getPayments() {
        return paymentMapper.paymentsToDTO(paymentRepository.findAll());
    }


    @Transactional
    public PaymentDtoGet addPayment(PaymentDtoPost paymentDtoPost) throws UserNotFoundException {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setDate(LocalDate.parse(paymentDtoPost.getDate()));
        paymentEntity.setAmount(paymentDtoPost.getAmount());
        paymentEntity.setDescription(paymentDtoPost.getDescription());

        UserEntity userEntity = userService.findUserById(Long.parseLong(paymentDtoPost.getUser()));
        paymentEntity.setUser(userEntity);
        userEntity.getPayments().add(paymentEntity);

        return paymentMapper.paymentToDo(paymentRepository.save(paymentEntity));
    }

    @Transactional
    public PaymentDtoGet updatePayment(PaymentDtoPost paymentDtoPost) throws PaymentNotFoundException {
        PaymentEntity paymentEntity = paymentRepository.findById(paymentDtoPost.getId()).orElseThrow(() -> new PaymentNotFoundException(
                "Payment with id " + paymentDtoPost.getId() + " does not exist"));

        if (paymentDtoPost.getDate()!= null &&
                paymentDtoPost.getDate().length() > 0 &&
                !Objects.equals((paymentEntity.getDate()), paymentDtoPost.getDate())) {
            paymentEntity.setDate(LocalDate.parse(paymentDtoPost.getDate()));
        }

        return paymentMapper.paymentToDo(paymentEntity);
    }

    @Transactional
    public PaymentEntity findPaymentById(Long id) throws PaymentNotFoundException {
        return paymentRepository.findById(id).orElseThrow(() -> new PaymentNotFoundException(
                "Payment with id " + id + " does not exist")) ;
    }

    @Transactional
    public void deletePayment(Long id) throws PaymentNotFoundException {
        boolean exist = paymentRepository.existsById(id);
        if(!exist){
            throw new PaymentNotFoundException("Payment with id " + id + " does not exist");
        }
        paymentRepository.deleteById(id);
    }

//    @Transactional
//    public void addNewBill(PaymentEntity paymentEntity) {
//        Optional<PaymentEntity> billById = paymentRepository.findById(paymentEntity.getId());
//        if (billById.isPresent()) {
//            throw new IllegalStateException("id taken ");
//        }
//        paymentRepository.save(paymentEntity);
//        System.out.println(paymentEntity);
//    }
//

}
