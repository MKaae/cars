package dat3.car.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Reservation extends AdminDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate rentalDate;
    @ManyToOne
    private Member member;
    @ManyToOne
    private Car car;

    public Reservation(LocalDate rentalDate,Member member, Car car) {
        this.rentalDate = rentalDate;
        this.member = member;
        this.car = car;
        member.addReservation(this);
        car.addReservation(this);
    }
}
