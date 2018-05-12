package ch.bfh.bti7081.s2018.green.models.entities;

import ch.bfh.bti7081.s2018.green.models.enumerations.StaffType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Staff")
public class Staff extends Person {

    @Column(name = "type")
    private StaffType staffType;

    public Staff(int id, String firstName, String lastName, Date birthDate, String address, String zip, String city, String email, String phone, StaffType staffType) {
        super(id, firstName, lastName, birthDate, address, zip, city, email, phone);
        this.staffType = staffType;
    }

    public StaffType getStaffType() {
        return staffType;
    }

    public void setStaffType(StaffType staffType) {
        this.staffType = staffType;
    }
}
