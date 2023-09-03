package dat3.car.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.entity.Car;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor //A must for builder
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarRequest {
    private int id;
    private String brand;
    private String model;
    private double pricePerDay;
    private int bestDiscount;

    public static Car getCarEntity(CarRequest carRequest){
        return new Car(carRequest.getBrand(), carRequest.getModel(), carRequest.getPricePerDay(), carRequest.bestDiscount);
    }
    public CarRequest(Car car){
        this.id = car.getId();
        this.brand = car.getBrand();
        this.model = car.getModel();
        this.pricePerDay = car.getPricePrDay();
        this.bestDiscount = car.getBestDiscount();
    }
}
