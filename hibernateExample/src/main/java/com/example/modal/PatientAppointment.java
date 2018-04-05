package com.example.modal;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="appointment")
public class PatientAppointment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointment_sequence")
    @SequenceGenerator(name="appointment_sequence", sequenceName = "appointment_seq", allocationSize = 1)
    private int Id;


    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;
    /*@Column(name="date")
    public  String date;*/

    @Column(name = "time")
    public String time;

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

 /*   public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }*/

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;

    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @OneToOne (mappedBy="appointment")
    private Patient patient;


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

