package ch.bfh.bti7081.s2018.green.models.entities;

import ch.bfh.bti7081.s2018.green.models.enumerations.StaffType;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Staff extends Person {

    @Column(name = "type", nullable = false)
    private StaffType staffType;

    public Staff(){
        // required by JPA
    }

    public Staff(String firstName, String lastName, Date birthDate, String address, String zip, String city, String email, String phone, StaffType staffType) {
        super(firstName, lastName, birthDate, address, zip, city, email, phone);
        this.staffType = staffType;
    }

    public StaffType getStaffType() {
        return staffType;
    }

    public void setStaffType(StaffType staffType) {
        this.staffType = staffType;
    }
}
