package com.example.modal;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "hospital")
public class Hospital implements Serializable {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hospital_sequence")
    @SequenceGenerator(name = "hospital_sequence",sequenceName = "hospital_seq",allocationSize = 1)
    private int Id;
    @NotNull
    @Column(name = "name")
    private String name;
    @NotNull
    @Column(name = "city_name")
    private String cityName;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Doctor> doctors;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }
}
