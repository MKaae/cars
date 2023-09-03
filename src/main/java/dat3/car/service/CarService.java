package dat3.car.service;

import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CarService {
    CarRepository carRepository;

    public CarService(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    public List<CarResponse> getCars(boolean includeAll) {
        List<Car> cars = carRepository.findAll();
        return cars.stream().map((car-> new CarResponse(car, includeAll))).toList();
    }
    public CarResponse findById(int id) {
        Car car = carRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Car with this id doesn't exist"));
        return new CarResponse(car, true);
    }

    public CarResponse addCar(CarRequest body) {
        Car newCar = CarRequest.getCarEntity(body);
        newCar = carRepository.save(newCar);
        return new CarResponse(newCar, true);
    }

    public ResponseEntity<Boolean> editCarInfo(CarRequest body, int id) {
        Car car = carRepository.findById(id).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car exist with this id"));
        if(!String.valueOf(body.getId()).equals(String.valueOf(id))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot change id");
        }
        car.setBrand(body.getBrand());
        car.setModel(body.getModel());
        car.setPricePrDay(body.getPricePerDay());
        car.setBestDiscount(body.getBestDiscount());
        carRepository.save(car);
        return ResponseEntity.ok(true);
    }
    private Car getCarById(int id){
        return carRepository.findById(id).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Car with this id does not exist"));
    }
    public void setCarDiscount(int id, int value) {
        Car car = getCarById(id);
        car.setBestDiscount(value);
        carRepository.save(car);
    }

    public void deleteCarById(int id) {
        Car car = getCarById(id);
        carRepository.delete(car);
    }
}
