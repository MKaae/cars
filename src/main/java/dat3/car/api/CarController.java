package dat3.car.api;

import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cars")
public class CarController {

    CarService carService;
    public CarController(CarService carService){
        this.carService = carService;
    }
    //Security - Anonymous
    @GetMapping
    List<CarResponse> getCars(){
        return carService.getCars(false);
    }
    @GetMapping("/admin")
    List<CarResponse> getCarsAll(){
        return carService.getCars(true);
    }

    @GetMapping(path = "/{id}")
    CarResponse getCarById(@PathVariable int id) throws Exception {
        return carService.findById(id);
    }
    //Security - Admin
    @PostMapping()
    CarResponse addCar(@RequestBody CarRequest body){
        return carService.addCar(body);
    }
    //Security - Admin
    @PutMapping("/{id}")
    ResponseEntity<Boolean> editCarInfo(@RequestBody CarRequest body, @PathVariable int id){
        return carService.editCarInfo(body, id);
    }
    //Security - Admin
    @PatchMapping("/discount/{id}/{value}")
    void setCarDiscount(@PathVariable int id, @PathVariable int value){
        carService.setCarDiscount(id, value);
    }
    //Security - Admin
    @DeleteMapping("/id")
    void deleteCarById(@PathVariable int id){
        carService.deleteCarById(id);
    }
    @GetMapping("/average")
    double findAveragePricePrDay(){
        return carService.findAveragePricePrDay();
    }
    @GetMapping("/unreserved")
    List<CarResponse> findNotReservedCars(){
        return carService.findNotReservedCars();
    }
    @GetMapping("/{brand}/{model}")
    List<CarResponse> findByBrandAndModel(@PathVariable String brand, @PathVariable String model){
        return carService.findCarByBrandAndModel(brand, model);
    }
}
