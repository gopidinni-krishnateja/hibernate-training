package com.example.modal;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotEmpty
    @Column(name="first_name")
    @Length(min = 5,max = 10)
    private String firstName;

    @NotEmpty
    @Column(name="last_name")
    @Length(min = 5,max = 10)
    private String lastName;

    @NotEmpty
    @NotNull
    @Column(name = "type")
    public String type;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL,orphanRemoval = true, fetch=FetchType.LAZY)
    private List<PatientAppointment> appointments;

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    @ManyToOne(fetch = FetchType.EAGER)
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
