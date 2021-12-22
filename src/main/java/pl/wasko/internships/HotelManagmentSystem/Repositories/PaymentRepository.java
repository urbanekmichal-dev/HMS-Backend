package pl.wasko.internships.HotelManagmentSystem.Repositories;

import pl.wasko.internships.HotelManagmentSystem.Entities.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository <PaymentEntity,Long>{

    //@Query("Select b From Bill b where b.id = ?1")
    //Optional<Bill> findBillById(Long id);

}
