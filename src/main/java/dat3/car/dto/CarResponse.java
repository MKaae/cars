package dat3.car.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.entity.Car;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor //A must for builder
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarResponse {
    private int id;
    private String brand;
    private String model;
    private double pricePerDay;
    private Integer bestDiscount;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    private LocalDateTime created;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    private LocalDateTime edited;

    public CarResponse(Car car, boolean includeAll) {
        this.id = car.getId();
        this.brand = car.getBrand();
        this.model = car.getModel();
        this.pricePerDay = car.getPricePrDay();
        if(includeAll){
            this.created = car.getCreated();
            this.edited = car.getEdited();
            this.bestDiscount = car.getBestDiscount();
        }
    }
}
