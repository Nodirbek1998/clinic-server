package uz.cas.demo.entity;

import uz.cas.demo.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Queue extends AbsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Users user;
    @OneToOne
    private Patient patient;

    private String name;

    private boolean accepted;

    private boolean status;


    public Queue() {
    }

    public Queue(Users user, Patient patient, String name, boolean accepted) {
        this.user = user;
        this.patient = patient;
        this.name = name;
        this.accepted = accepted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
