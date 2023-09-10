package dat3.car.repository;

import dat3.car.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findCarByBrandAndModel(String brand, String model);
    List<Car> findByReservationsIsNull();
    //Query created with help from ChatGPT
    @Query("SELECT car FROM Car car WHERE car.bestDiscount = (SELECT MAX(car.bestDiscount) FROM Car car)")
    List<Car> findAllByBestDiscount();
    //Query created with help from ChatGPT
    @Query(value = "SELECT AVG(car.pricePrDay) FROM Car car")
    double findAveragePricePerDay();
}
