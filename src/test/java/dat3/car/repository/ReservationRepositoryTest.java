package dat3.car.repository;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ReservationRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired
    ReservationRepository reservationRepository;

    boolean isDataInitialized;
    private Car car;
    private Member member;
    @BeforeEach
    void setUp(){
        if(isDataInitialized) return;
        car = carRepository.save(new Car("Toyota", "Corolla", 675, 15));
        member = memberRepository.save(new Member("farblossom", "123456", "abc@123.dk", "Jens",
                "Jensen", "Hvidovrevej 5", "Hvidovre", "2500"));
        reservationRepository.save(new Reservation(LocalDate.of(2222, 2, 2), member, car));
        isDataInitialized = true;
    }
    @Test
    void testDoesCarExistByDate(){
        assertTrue(reservationRepository.existsByCar_IdAndRentalDate(car.getId(), LocalDate.of(2222,2,2)));
    }
    @Test
    void testReservationsByUsername(){
        assertEquals(1, reservationRepository.findByMemberUsername(member.getUsername()).size());
    }
    @Test
    void testFindReservationsNotExistingUsername(){
        assertEquals(0, reservationRepository.findByMemberUsername("I dont exist").size());
    }
}