package ch.bfh.bti7081.s2018.green.models.entities;

import javax.persistence.*;

@Entity
public class Therapy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private int id;

    @Column(nullable = false)
    private String name;

    public Therapy() {}

    public Therapy(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
