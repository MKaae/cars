package dat3.car.service;

import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceMockitoTest {
    private CarService carService;
    @Mock
    private CarRepository carRepository;
    @BeforeEach
    void setUp(){
        carService = new CarService(carRepository);
    }
    private Car makeCar(int id, String brand, String model, double pricePerDay, int bestDiscount){
        Car car = new Car(id, brand, model, pricePerDay, bestDiscount);
        car.setCreated(LocalDateTime.now());
        car.setEdited(LocalDateTime.now());
        return car;
    }
    @Test
    public void testGetCars(){
        Car car1 = makeCar(1, "brand1", "model1", 100, 10);
        Car car2 = makeCar(2, "brand2", "model2", 200, 20);
        when(carRepository.findAll()).thenReturn(List.of(car1, car2));
        List<CarResponse> responses = carService.getCars(true);
        assertEquals(2, responses.size());
        assertNotNull(responses.get(0).getCreated());
    }
    @Test
    public void testFindingById(){
        when(carRepository.findById(1)).thenReturn(Optional.of(makeCar
                (1, "brand1", "model1", 100, 10)));
        CarResponse response = carService.findById(1);
        assertEquals(1, response.getId());
    }
    @Test
    public void testAddCarSuccess(){
        Car newCar = makeCar(3, "brand3", "model3", 300, 30);
        when(carRepository.save(any(Car.class))).thenReturn(newCar);
        CarRequest request = new CarRequest(newCar);
        CarResponse response = carService.addCar(request);
        assertEquals(3, response.getId());
    }
    //Method created with help from ChatGPT.
    @Test
    public void testEditCarInfo(){
        CarRequest request = new CarRequest(1, "brand1", "model1", 100, 10);
        Car expectedCar = makeCar(1, "brand2", "model2", 200, 20);
        expectedCar.setPricePrDay(100);
        expectedCar.setBrand("brand1");
        expectedCar.setModel("model1");
        expectedCar.setBestDiscount(10);
        when(carRepository.save(any(Car.class))).thenReturn(expectedCar);
        CarResponse response = carService.addCar(request);
        verify(carRepository).save(any(Car.class));
        assertEquals(expectedCar.getPricePrDay(), response.getPricePerDay());
        assertEquals(expectedCar.getBrand(), response.getBrand());
        assertEquals(expectedCar.getModel(), response.getModel());
        assertEquals(expectedCar.getBestDiscount(), response.getBestDiscount());
    }
    @Test
    public void testSetBestDiscountPriceCarFound(){
        Car newCar = makeCar(3, "brand3", "model3", 300, 30);
        when(carRepository.findById(3)).thenReturn(Optional.of(newCar));
        int testBestDiscount = 10;

        when(carRepository.save(newCar)).thenReturn(newCar);
        carService.setCarDiscount(3, testBestDiscount);
        assertEquals(testBestDiscount, newCar.getBestDiscount());
        verify(carRepository).save(newCar);
    }
    @Test
    public void TestSetBestDiscountCarNotFound(){
        int testId = 5;
        when(carRepository.findById(testId)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, ()-> carService.setCarDiscount(testId, 10));
    }
    @Test
    public void testDeleteCar(){
        int testId = 5;
        Car newCar = new Car();
        newCar.setId(testId);

        when(carRepository.findById(testId)).thenReturn(Optional.of(newCar));
        carService.deleteCarById(testId);
        verify(carRepository).delete(newCar);
    }
    @Test
    public void testDeleteCarCarNotFound(){
        int testId = 5;
        when(carRepository.findById(testId)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, ()-> carService.deleteCarById(testId));
    }
}