package ch.bfh.bti7081.s2018.green.models.entities;

import ch.bfh.bti7081.s2018.green.models.enumerations.StaffType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Staff extends Person {

    @Column(nullable = false)
    private StaffType type;

    public Staff(){
        // required by JPA
    }

    public Staff(String firstName, String lastName, LocalDate dob, String address, String zip, String city, String email, String phone, StaffType type) {
        super(firstName, lastName, dob, address, zip, city, email, phone);
        this.type = type;
    }

    public StaffType getType() {
        return type;
    }

    public void setType(StaffType type) {
        this.type = type;
    }
}
