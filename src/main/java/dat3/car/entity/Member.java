package dat3.car.entity;

import dat3.security.entity.UserWithRoles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE")
public class Member extends UserWithRoles {
    @Column(length = 40)
    private String firstName;
    @Column(length = 40)
    private String lastName;
    @Column(length = 50)
    private String street;
    @Column(length = 50)
    private String city;
    @Column(length = 20)
    private String zip;
    private boolean approved;
    private int ranking;
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Reservation> reservations = new ArrayList<>();

    public void addReservation(Reservation reservation){
            reservations.add(reservation);
    }

    public Member(String username, String password, String email, String firstName, String lastName, String street,
                  String city, String zip){
        super(username, password, email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
    }
}

