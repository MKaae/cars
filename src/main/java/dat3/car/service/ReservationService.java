package dat3.car.service;

import dat3.car.dto.ReservationRequest;
import dat3.car.dto.ReservationResponse;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {
    MemberRepository memberRepository;
    CarRepository carRepository;
    ReservationRepository reservationRepository;

    public ReservationService(MemberRepository memberService, CarRepository carRepository, ReservationRepository reservationRepository) {
        this.memberRepository = memberService;
        this.carRepository = carRepository;
        this.reservationRepository = reservationRepository;
    }

    public ReservationResponse reserveCar(ReservationRequest body){
        if(body.getDate().isBefore(LocalDate.now())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot reserve at date in the past.");
        }
        Member member = memberRepository.findById(body.getUsername()).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Member with this username does exist"));
        Car car = carRepository.findById(body.getCarId()).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Car with this id does exist"));
        if(reservationRepository.existsByCar_IdAndRentalDate(body.getCarId(), body.getDate())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car is already reserved on this date");
        }
        Reservation res = reservationRepository.save(new Reservation(body.getDate(), member, car));
        return new ReservationResponse(res);
    }

    public List<ReservationResponse> getReservationsForUser(String username) {
        List<Reservation> reservations = reservationRepository.findByMemberUsername(username);
        return reservations.stream().map(res -> new ReservationResponse(res)).toList();
    }
}
