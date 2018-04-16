package com.example.service;

import com.example.dao.HospitalDAO;
import com.example.modal.Doctor;
import com.example.modal.Hospital;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class HospitalServiceTest {
    Hospital hospital=new Hospital();
    @Mock
    HospitalDAO hospitalDAO;
    @InjectMocks
    HospitalServiceimpl hospitalServiceimpl;
    @Spy
    List<Hospital> hospitals = new ArrayList<Hospital>();
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        hospitals = getHospitals();

    }

    public List<Hospital> getHospitals() {
        Hospital hospital=new Hospital();
        Doctor doctor=new Doctor();
        doctor.setId(1);
        Doctor doctor1=new Doctor();
        doctor.setId(2);
        List<Doctor> doctors=new ArrayList<Doctor>();
        doctors.add(doctor);
        doctors.add(doctor1);
        hospital.setId(1);
        hospital.setName("CARE");
        hospital.setCityName("Hyderabad");
        hospital.setDoctors(doctors);
        Hospital hospital1=new Hospital();
        hospital1.setName("Yashodha");
        hospital1.setCityName("Bangalore");
        hospital1.setId(2);
        hospital1.setDoctors(doctors);
        hospitals.add(hospital);
        hospitals.add(hospital1);
        return hospitals;
    }

    @Test
    public void addHospitalTest(){
        doNothing().when(hospitalDAO).addHospital(hospital);
        hospitalServiceimpl.addHospital(hospital);
        verify(hospitalDAO,atLeastOnce()).addHospital(hospital);
    }
    @Test
    public void deleteHospitalTest(){
        int id=1;
        hospital.setId(id);
        doNothing().when(hospitalDAO).deleteHospital(id);
        hospitalServiceimpl.deleteHospital(id);
        verify(hospitalDAO,atLeastOnce()).deleteHospital(id);
    }
    @Test
    public void updateHospital(){
        when(hospitalDAO.updateHospital(hospital)).thenReturn(hospital);
        hospitalServiceimpl.updateHospital(hospital);
        verify(hospitalDAO,atLeastOnce()).updateHospital(hospital);
    }
    @Test
    public void getAllHospitals(){
        when(hospitalDAO.getAllHospitals()).thenReturn(hospitals);
        Assert.assertEquals(hospitalServiceimpl.getAllHospitals(),hospitals);
    }
    @Test
    public void getHospitalTest(){
        int id=1;
        hospital.setId(id);
        when(hospitalDAO.getHospital(id)).thenReturn(hospital);
        hospitalServiceimpl.getHospital(id);
        verify(hospitalDAO,atLeastOnce()).getHospital(id);
    }
}
