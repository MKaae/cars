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
class CarRepositoryTest {

    @Autowired
    CarRepository carRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ReservationRepository reservationRepository;

    boolean isInitialized = false;
    private Car car2;
    private Member member1;
    @BeforeEach
    void setUp() {
        if(!isInitialized){
            carRepository.deleteAll();
            Car car1 = carRepository.save(new Car("Toyota", "Corolla", 675, 15));
            car2 = carRepository.save(new Car("Ford", "Focus", 565, 30));
            member1 = memberRepository.save(new Member("fartblossom", "123456", "abc@123.dk", "Jens",
                    "Jensen", "Hvidovrevej 5", "Hvidovre", "2500"));
            reservationRepository.save(new Reservation(LocalDate.of(2222,2,2), member1, car1));
            isInitialized = true;
        }
    }
    @Test
    void deleteAll(){
        reservationRepository.deleteAll();
        memberRepository.deleteAll();
        carRepository.deleteAll();
        assertEquals(0, carRepository.count());
    }
    @Test
    void testAll(){
        Long count = carRepository.count();
        assertEquals(2, count);
    }
    @Test
    void testAveragePricePrDay(){
        assertEquals(620, carRepository.findAveragePricePerDay());
    }
    @Test
    void testFindByBestDiscount(){
        assertEquals(1, carRepository.findAllByBestDiscount().size());
    }
    @Test
    void testFindUnreservedCars(){
        assertEquals(1, carRepository.findByReservationsIsNull().size());
        reservationRepository.save(new Reservation(LocalDate.of(2222,2,2), member1, car2));
        assertEquals(0, carRepository.findByReservationsIsNull().size());
    }
    @Test
    void testFindByBrandAndModel(){
        assertEquals(1, carRepository.findCarByBrandAndModel(car2.getBrand(), car2.getModel()).size());
    }
}