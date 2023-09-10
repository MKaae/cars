package dat3.car.repository;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired
    ReservationRepository reservationRepository;

    boolean isInitialized = false;
    @BeforeEach
    void setUp() {
        if(isInitialized) return;
        memberRepository.deleteAll();
        Member member1 = memberRepository.save(new Member("fartblossom", "123456", "abc@123.dk", "Jens",
                "Jensen", "Hvidovrevej 5", "Hvidovre", "2500"));
        Member member2 = memberRepository.save(new Member("eggbasket", "password123", "email@email.dk", "Hans", "Hansen",
                "Folehaven 1", "Valby", "2500"));
        Car car = carRepository.save(new Car("Toyota", "Corolla", 675, 15));
        reservationRepository.save(new Reservation(LocalDate.of(2222,2,2), member1, car));
        isInitialized = true;

    }
    @Test
    void deleteAll(){
        reservationRepository.deleteAll();
        carRepository.deleteAll();
        memberRepository.deleteAll();
        assertEquals(0, memberRepository.count());
    }
    @Test
    void testAll(){
        Long count = memberRepository.count();
        assertEquals(2, count);
    }
    @Test
    void testFindMemberByReservationNotNull(){
        List<Member> membersThatHaveReservations = memberRepository.findMembersByReservationsIsNotNull();
        assertEquals(1, membersThatHaveReservations.size());
        assertEquals("fartblossom", membersThatHaveReservations.get(0).getUsername());
    }
}
