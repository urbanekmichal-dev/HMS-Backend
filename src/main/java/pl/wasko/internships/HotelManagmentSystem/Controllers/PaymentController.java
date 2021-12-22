package pl.wasko.internships.HotelManagmentSystem.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.wasko.internships.HotelManagmentSystem.DTO.PaymentDTO.PaymentDtoGet;
import pl.wasko.internships.HotelManagmentSystem.DTO.PaymentDTO.PaymentDtoPost;
import pl.wasko.internships.HotelManagmentSystem.Entities.PaymentEntity;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.PaymentNotFoundException;
import pl.wasko.internships.HotelManagmentSystem.Exceptions.UserNotFoundException;
import pl.wasko.internships.HotelManagmentSystem.Services.impl.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/payments")
public class PaymentController {
    private final PaymentServiceImpl paymentServiceImpl;

    @Autowired
    public PaymentController(PaymentServiceImpl paymentServiceImpl) {
        this.paymentServiceImpl = paymentServiceImpl;
    }


    @GetMapping("/")
    public ResponseEntity<List<PaymentDtoGet>> getRoles() {
        return new ResponseEntity<>(paymentServiceImpl.getPayments(), HttpStatus.OK);
    }

    @PostMapping("/")
    public void addPayment(@RequestBody PaymentDtoPost paymentDtoPost) throws UserNotFoundException {
        paymentServiceImpl.addPayment(paymentDtoPost);
    }

    @DeleteMapping("/")
    public void deletePayment(
            @RequestBody PaymentDtoPost paymentDtoPost) throws PaymentNotFoundException {
        paymentServiceImpl.deletePayment(paymentDtoPost.getId());

    }

    @PutMapping("/")
    public ResponseEntity<PaymentDtoGet> updatePayment(
            @RequestBody PaymentDtoPost paymentDtoPost) throws PaymentNotFoundException {
       PaymentDtoGet paymentDtoGet= paymentServiceImpl.updatePayment(paymentDtoPost);
       return new ResponseEntity<>(paymentDtoGet,HttpStatus.OK);

    }

}
