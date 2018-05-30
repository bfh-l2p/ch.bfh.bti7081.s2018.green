package ch.bfh.bti7081.s2018.green.models.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

@Entity
@NamedQuery(name="JournalEntry.findByPatient", query="SELECT j FROM JournalEntry j where patientId = :patientId order by created asc")
public class JournalEntry {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false, updatable = false)
    public LocalDateTime created;

    @OneToOne
    @JoinColumn(name = "patientId", nullable = false)
    private Patient patient;

    @OneToOne
    @JoinColumn(name = "authorId", nullable = false)
    private Staff staff;

    public JournalEntry() {
        // required by JPA
    }

	public JournalEntry(String content, Patient patient, Staff staff) {
		this.content = content;
		this.staff = staff;
		this.patient = patient;
	}

    @PrePersist
    public void onPrePersist() {
        created = LocalDateTime.now();
    }

	public int getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Staff getStaff() {
		return staff;
	}

	public Patient getPatient() {
	    return patient;
	}

	public LocalDateTime getCreated() {
	    return created;
	}
}
