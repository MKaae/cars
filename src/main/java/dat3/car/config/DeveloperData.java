package dat3.car.config;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DeveloperData implements ApplicationRunner {
    CarRepository carRepository;
    MemberRepository memberRepository;
    public DeveloperData(CarRepository carRepository, MemberRepository memberRepository){
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("Toyota", "Corolla", 675, 15));
        cars.add(new Car("Ford", "Focus", 560, 30));
        cars.add(new Car("Chevrolet", "Cruze", 806, 25));
        cars.add(new Car("Nissan", "Sentra", 257, 40));
        cars.add(new Car("Honda", "Civic", 441, 5));
        cars.add(new Car("Toyota", "Corolla", 562, 10));
        cars.add(new Car("Chevrolet", "Cruze", 292, 25));
        cars.add(new Car("Ford", "Focus", 481, 20));
        cars.add(new Car("Nissan", "Sentra", 594, 2));
        cars.add(new Car("Honda", "Civic", 362, 10));
        cars.add(new Car("Chevrolet", "Cruze", 295, 20));
        cars.add(new Car("Toyota", "Corolla", 370, 30));
        cars.add(new Car("Ford", "Focus", 255, 40));
        cars.add(new Car("Nissan", "Sentra", 374, 15));
        cars.add(new Car("Honda", "Civic", 705, 5));
        cars.add(new Car("Toyota", "Corolla", 962, 45));
        cars.add(new Car("Chevrolet", "Cruze", 759, 15));
        cars.add(new Car("Ford", "Focus", 884, 20));
        cars.add(new Car("Nissan", "Sentra", 956, 25));
        cars.add(new Car("Honda", "Civic", 733, 5));
        cars.add(new Car("Toyota", "Corolla", 598, 35));
        cars.add(new Car("Chevrolet", "Cruze", 689, 20));
        cars.add(new Car("Ford", "Focus", 737, 10));
        cars.add(new Car("Nissan", "Sentra", 318, 30));
        cars.add(new Car("Honda", "Civic", 875, 45));
        cars.add(new Car("Toyota", "Corolla", 848, 35));
        cars.add(new Car("Chevrolet", "Cruze", 521, 25));
        cars.add(new Car("Ford", "Focus", 785, 2));
        cars.add(new Car("Nissan", "Sentra", 711, 10));
        cars.add(new Car("Honda", "Civic", 579, 30));
        cars.add(new Car("Toyota", "Corolla", 977, 20));
        cars.add(new Car("Chevrolet", "Cruze", 405, 15));
        cars.add(new Car("Ford", "Focus", 815, 5));
        cars.add(new Car("Nissan", "Sentra", 695, 40));
        cars.add(new Car("Honda", "Civic", 398, 45));
        cars.add(new Car("Toyota", "Corolla", 734, 20));
        cars.add(new Car("Chevrolet", "Cruze", 222, 10));
        cars.add(new Car("Ford", "Focus", 434, 5));
        cars.add(new Car("Nissan", "Sentra", 820, 15));
        cars.add(new Car("Honda", "Civic", 722, 10));
        cars.add(new Car("Toyota", "Corolla", 561, 35));
        cars.add(new Car("Chevrolet", "Cruze", 299, 20));
        cars.add(new Car("Ford", "Focus", 755, 2));
        cars.add(new Car("Nissan", "Sentra", 373, 10));
        cars.add(new Car("Honda", "Civic", 321, 30));
        cars.add(new Car("Toyota", "Corolla", 470, 20));
        cars.add(new Car("Ford", "Focus", 755, 2));
        cars.add(new Car("Nissan", "Sentra", 373, 10));
        cars.add(new Car("Honda", "Civic", 321, 30));
        cars.add(new Car("Toyota", "Corolla", 470, 20));
        carRepository.saveAll(cars);

        List<Member> members = new ArrayList<>();
        members.add(new Member("farblossom", "123456", "abc@123.dk", "Jens",
                "Jensen", "Hvidovrevej 5", "Hvidovre", "2500"));
        members.add(new Member("eggbasket", "password123", "email@email.dk", "Hans", "Hansen",
                "Folehaven 1", "Valby", "2500"));
        memberRepository.saveAll(members);
    }
}
