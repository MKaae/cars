package dat3.car.api;

import dat3.car.dto.ReservationRequest;
import dat3.car.dto.ReservationResponse;
import dat3.car.service.ReservationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    ReservationService reservationService;

    /*public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }*/
    //Security - User
    /*@PostMapping
    public ReservationResponse makeReservation(@RequestBody ReservationRequest body){
        return reservationService.reserveCar(body);
    }*/
    @GetMapping
    public List<ReservationResponse> getReservations(){
        List<ReservationResponse> res = reservationService.getReservations();
        return res;
    }
    @GetMapping("/reservations-for-authenticated")
    public List<ReservationResponse> getReservationsForUser(Principal principal){
        List<ReservationResponse> res = reservationService.getReservationsForUser(principal.getName());
        return res;
    }
    //("/{carId}/{date}")
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/v2") ///(@PathVariable int id, @PathVariable String date, Principal principal)
    public ReservationResponse makeReservation2(@RequestBody ReservationRequest body, Principal principal){
        System.out.println(body.getUsername());
        body.setUsername("");
        body.setUsername(principal.getName());
        return reservationService.reserveCar(body);
    }
    //Security - Admin
    @GetMapping("/{username}")
    public List<ReservationResponse> getReservationsForUser(@PathVariable String username){
        List<ReservationResponse> response = reservationService.getReservationsForUser(username);
        return response;
    }
}
