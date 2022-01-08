package pl.wasko.internships.HotelManagmentSystem.Config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.wasko.internships.HotelManagmentSystem.Entities.*;
import pl.wasko.internships.HotelManagmentSystem.Repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.wasko.internships.HotelManagmentSystem.Securityy.model.Role;
import pl.wasko.internships.HotelManagmentSystem.Securityy.model.User;
import pl.wasko.internships.HotelManagmentSystem.Securityy.repository.UserRepositoryy;


import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Configuration
public class Config {

   // @Bean//////////////////////UWAJJAJAJ
    CommandLineRunner commandLineRunner(RoomRepository roomRepository, BookingRepository bookingRepository, UserRepository userRepository, PaymentRepository paymentRepository,
                                        RoleRepository roleRepository,  PasswordEncoder passwordEncoder) {
        return args -> {
            BookingEntity booking1 = new BookingEntity(
                    LocalDate.of(2022, 1, 1),
                    LocalDate.of(2022, 1, 5));
            BookingEntity booking2 = new BookingEntity(
                    LocalDate.of(2022, 1, 7),
                    LocalDate.of(2022, 1, 15));
            BookingEntity booking3 = new BookingEntity(
                    LocalDate.of(2022, 1, 18),
                    LocalDate.of(2022, 1, 25));

            BookingEntity booking4 = new BookingEntity(
                    LocalDate.of(2017, 12, 10),
                    LocalDate.of(2017, 11, 12));

            BookingEntity booking5 = new BookingEntity(
                    LocalDate.of(2016, 12, 10),
                    LocalDate.of(2016, 11, 12));
            RoomEntity single = new RoomEntity(
                    RoomType.ROOM,
                    1,
                    399.99,
                    "https://i.ibb.co/GQT17Xf/24trending-shophotels1-super-Jumbo.jpg",
                    1,
                    1,
                    1,
                    "Radzionków",
                    "Piekny domek :)"
                    );

            RoomEntity doublee = new RoomEntity(
                    RoomType.FLAT,
                    2,
                    199.99,
                    "https://empire-s3-production.bobvila.com/pages/538/original/Bedroom.jpg",
                    2,
                    1,
                    1,
                    "Zbrosławice",
                    "Piekny apartament :)"
            );

            RoomEntity pentHouse = new RoomEntity(
                    RoomType.FLAT,
                    3,
                    499.40,
                    "https://cdn.pixabay.com/photo/2019/12/16/15/43/room-4699578_960_720.jpg",
                    2,
                    1,
                    2,
                    "Gliwice",
                    "Czysty penthouse"
            );



            RoomEntity zaebisty = new RoomEntity(
                    RoomType.FLAT,
                    4,
                    299.99,
                    "https://www.reviewpro.com/wp-content/uploads/2019/05/Hotel-deckchairs-vierw.jpeg",
                    3,
                    1,
                    2,
                    "Gliwice",
                    "Zadbana okolica i inne");

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

            User adminSystem = new User(
                    "Kris1",
                    passwordEncoder.encode("123"),
                    "michal@wp.pl",
                    Instant.now(),
                    true,
                    "Krzystof",
                    "Urbanek",
                    "234554",
                    "sadad",
                    Role.values()[1],
                    "ffdfd"
            );

            User adminSystem1 = new User(
                    "Kris2",
                    passwordEncoder.encode("123"),
                    "michal@wp.pl",
                    Instant.now(),
                    true,
                    "Krzystof",
                    "Urbanek",
                    "234554",
                    "asdad",
                    Role.values()[1],
                    "ffdfd"
            );

            User adminSystem2 = new User(
                    "Kris3",
                    passwordEncoder.encode("123"),
                    "michal@wp.pl",
                    Instant.now(),
                    true,
                    "Krzystof",
                    "Urbanek",
                    "234554",
                    "ssdad",
                    Role.values()[1],
                    "ffdfd"
            );


            ////TEST REZERWACJI
////////////////////////
            //Użytkownika->Rola !
            //Użytkownik->Płatności[]
            //Użytkownik->Rezerwacje[]



            michal.setRole(admin);
            booking1.setUser(michal);
            booking1.setRoom(single);
            booking1.setPayment(paymentEntity3);
            booking1.setOwner(adminSystem);
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
            booking2.setOwner(adminSystem1);
            superUser.getUsers().add(krzysztof);
            paymentEntity1.setBooking(booking2);
            paymentEntity1.setUser(krzysztof);
            single.getBookings().add(booking2);
            //bookingRepository.save(booking2);


           // UserEntity krzysiu=userRepository.findUserById(1L).get();
            booking3.setRoom(zaebisty);
            booking3.setPayment(paymentEntity4);
            booking3.setUser(krzysztof);
            booking3.setOwner(adminSystem2);
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
