package dat3.car.repository;

import dat3.car.entity.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarRepositoryTest {

    @Autowired
    CarRepository carRepository;

    boolean isInitialized = false;
    @BeforeEach
    void setUp() {
        if(!isInitialized){
            carRepository.deleteAll();
            carRepository.save(new Car("Toyota", "Corolla", 675, 15));
            carRepository.save(new Car("Ford", "Focus", 560, 30));
            isInitialized = true;
        }
    }
    @Test
    public void deleteAll(){
        carRepository.deleteAll();
        assertEquals(0, carRepository.count());
    }
    @Test
    public void testAll(){
        Long count = carRepository.count();
        assertEquals(2, count);
    }
}