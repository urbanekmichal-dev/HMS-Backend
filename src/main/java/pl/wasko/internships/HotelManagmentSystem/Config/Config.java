package pl.wasko.internships.HotelManagmentSystem.Config;

import org.apache.catalina.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.wasko.internships.HotelManagmentSystem.Entities.*;
import pl.wasko.internships.HotelManagmentSystem.Repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



import java.time.LocalDate;
import java.util.List;

@Configuration
public class Config {

    @Bean
    CommandLineRunner commandLineRunner(RoomRepository roomRepository, BookingRepository bookingRepository, UserRepository userRepository, PaymentRepository paymentRepository,
                                        RoleRepository roleRepository) {
        return args -> {
            BookingEntity booking1 = new BookingEntity(
                    LocalDate.of(2021, 12, 10),
                    LocalDate.of(2021, 12, 13));
            BookingEntity booking2 = new BookingEntity(
                    LocalDate.of(2019, 12, 10),
                    LocalDate.of(2019, 12, 12));
            BookingEntity booking3 = new BookingEntity(
                    LocalDate.of(2018, 12, 10),
                    LocalDate.of(2018, 11, 12));

            BookingEntity booking4 = new BookingEntity(
                    LocalDate.of(2017, 12, 10),
                    LocalDate.of(2017, 11, 12));

            BookingEntity booking5 = new BookingEntity(
                    LocalDate.of(2016, 12, 10),
                    LocalDate.of(2016, 11, 12));
            RoomEntity single = new RoomEntity(
                    "APARTMENT",
                    1,
                    399.99);

            RoomEntity doublee = new RoomEntity(
                    "STUDIO",
                    2,
                    199.99);

            RoomEntity pentHouse = new RoomEntity(
                    "PENTHOUSE",
                    3,
                    499.40);
            RoomEntity zaebisty = new RoomEntity(
                    "DOUBLE",
                    4,
                    299.99);

            UserEntity michal = new UserEntity(
                "Michał",
                "Urbanek",
                "795625071",
                "MicUrb5050",
                "Jokokajoko123"
        );

            UserEntity krzysztof = new UserEntity(
                    "Krzysztof",
                    "Kawior",
                    "938736524",
                    "Kris12",
                    "Kolokokk3"
            );

            UserEntity marian = new UserEntity(
                    "Marian",
                    "Wojak",
                    "28273364",
                    "Wojow2",
                    "appka2"
            );
            UserEntity zbigniew = new UserEntity(
                    "Zbyszek",
                    "Posejdon",
                    "93873364",
                    "Zbyszko",
                    "wzbbs"
            );

            UserEntity roman = new UserEntity(
                    "Roman",
                    "Pijok",
                    "328273364",
                    "Pijok3",
                    "popij"
            );
            UserEntity bogdan = new UserEntity(
                    "Bogdan",
                    "Nowok",
                    "328273364",
                    "Bodzio",
                    "bobik22"
            );

            PaymentEntity paymentEntity1 = new PaymentEntity( 1200.2,
                    LocalDate.of(2019, 12, 12),
                    "Dodatkowe śniadania i kolacje");
            PaymentEntity paymentEntity2 = new PaymentEntity( 1900.2,
                    LocalDate.of(2018, 12, 12),
                    "Dodatkowa dostawka dwóch łóżek i śniadania");
            PaymentEntity paymentEntity3 = new PaymentEntity( 2900.2,
                    LocalDate.of(2017, 12, 12),
                    "Dodatkowy pakiet powitalny i sprzatanie");
            PaymentEntity paymentEntity4 = new PaymentEntity( 2900.2,
                    LocalDate.of(2017, 12, 12),
                    "Brak dodatków");



            RoleEnity admin = new RoleEnity("ADMIN");
            RoleEnity customer = new RoleEnity("GUEST");
            RoleEnity superUser = new RoleEnity("WORKER");



            ////TEST REZERWACJI
////////////////////////
            //Użytkownika->Rola !
            //Użytkownik->Płatności[]
            //Użytkownik->Rezerwacje[]



            michal.setRole(admin);
            booking1.setUser(michal);
            booking1.setRoom(single);
            booking1.setPayment(paymentEntity3);
            michal.getPayments().add(paymentEntity3);
            michal.getBookings().add(booking1);
            admin.getUsers().add(michal);
            paymentEntity3.setBooking(booking1);
            paymentEntity3.setUser(michal);
            bookingRepository.save(booking1);

            roomRepository.save(pentHouse);


            krzysztof.setRole(superUser);
            krzysztof.getPayments().add(paymentEntity1);
            krzysztof.getBookings().add(booking2);
            booking2.setUser(krzysztof);
            booking2.setRoom(doublee);
            booking2.setPayment(paymentEntity1);
            superUser.getUsers().add(krzysztof);
            paymentEntity1.setBooking(booking2);
            paymentEntity1.setUser(krzysztof);
            single.getBookings().add(booking2);
            //bookingRepository.save(booking2);


           // UserEntity krzysiu=userRepository.findUserById(1L).get();
            booking3.setRoom(zaebisty);
            booking3.setPayment(paymentEntity4);
            booking3.setUser(krzysztof);
            zaebisty.getBookings().add(booking3);
            paymentEntity4.setUser(krzysztof);
            paymentEntity4.setBooking(booking3);
            krzysztof.getBookings().add(booking3);
            krzysztof.getPayments().add(paymentEntity4);
            bookingRepository.save(booking3);
            bookingRepository.save(booking2);


            marian.setRole(customer);
            zbigniew.setRole(customer);
            roman.setRole(customer);
            bogdan.setRole(customer);
            userRepository.saveAll(List.of(marian,zbigniew,roman,bogdan));

//            michal.setRole(admin);
//            booking1.setUser(michal);
//            booking1.setRoom(single);
//            booking1.setPayment(paymentEntity3);
//
//            booking2.setRoom(doublee);
//            booking2.setUser(michal);
//            booking2.setPayment(paymentEntity4);
//
//            michal.getBookings().add(booking1);
//            michal.getBookings().add(booking2);
//            michal.getPayments().add(paymentEntity1);
//            michal.getPayments().add(paymentEntity2);
//
//
//            paymentEntity1.setUser(michal);
//            paymentEntity2.setUser(michal);
//            paymentEntity3.setBooking(booking1);
//            paymentEntity3.setBooking(booking2);
//
//            userRepository.save(michal) ;
/////////////////////////////////////
            //Rezerwacja -> Użytkownik
            //Rezerwacja -? Pokój !

         //   krzysztof.setRole(customer);

         //   booking3.setUser(krzysztof);
         //   booking3.setRoom(pentHouse);
//
//            booking2.setUser(krzysztof);
//            booking2.setRoom(pentHouse);
           // bookingRepository.save(booking3);
//////////////////////////////////////////////////////
            //Pokój-> Rezerwacje
            //marian.setRole(superUser);
//            booking4.setUser(bogdan);
//            booking4.setRoom(single);
//            booking5.setRoom();
//            zaebisty.getBookings().add(booking4);
//            zaebisty.getBookings().add(booking5);
//            roomRepository.save(zaebisty);


//            //Płatność -> Użytkownik
//            paymentEntity3.setUser(roman);
//            paymentRepository.save(paymentEntity3);
//
//
//            //Rola->Użytkownik
//            superUser.getUsers().add(zbigniew);
//            superUser.getUsers().add(bogdan);
//            roleRepository.save(superUser);
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            //roleRepository.save(superUser);





        };
    }
}
