package com.example.modal;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@NamedNativeQueries({@NamedNativeQuery(name="findAllDoctors",query ="SELECT * from doctor WHERE hospital_id IS NOT NULL ",resultClass = Doctor.class)})
@Entity
@Table(name = "doctor")
public class Doctor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_sequence")
    @SequenceGenerator(name="doctor_sequence", sequenceName = "doctor_seq", allocationSize = 1)
    private int Id;

    @NotNull
    @Column(name="first_name")
    private String firstName;

    @NotNull
    @Column(name="last_name")
    private String lastName;
    @NotNull
    @Column(name = "type")
    public String type;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL,orphanRemoval = true, fetch=FetchType.EAGER)
    private List<PatientAppointment> appointments;

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    public List<PatientAppointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<PatientAppointment> appointments) {
        this.appointments = appointments;
    }




    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
