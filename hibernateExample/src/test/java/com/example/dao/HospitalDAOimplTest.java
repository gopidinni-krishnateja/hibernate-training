package com.example.dao;

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
import static org.mockito.Mockito.when;

public class HospitalDAOimplTest {
    Hospital hospital =new Hospital();
    @Mock
    HospitalDAO hospitalDAO;
    @InjectMocks
    HospitalDAOimpl hospitalDAOimpl;
    @Spy
    List<Hospital> hospitals = new ArrayList<Hospital>();
    @Before
    public void setUp() {
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
    @Test(expected = RuntimeException.class)
    public void testAddHospital(){
        Assert.assertNotNull(hospitalDAO);
        doThrow(RuntimeException.class).when(hospitalDAO).addHospital(hospital);
        hospitalDAOimpl.addHospital(hospital);
        verify(hospitalDAOimpl, atLeastOnce()).addHospital(hospital);
    }
    @Test(expected = RuntimeException.class)
    public void testDeleteHospital(){
        Assert.assertNotNull(hospitalDAO);
        doThrow(RuntimeException.class).when(hospitalDAO).deleteHospital(1);
        hospitalDAOimpl.deleteHospital(1);
        verify(hospitalDAOimpl, atLeastOnce()).deleteHospital(1);
    }
    @Test(expected = RuntimeException.class)
    public void updateHospital(){
        Assert.assertNotNull(hospitalDAO);
        doThrow(RuntimeException.class).when(hospitalDAO).updateHospital(hospital);
        hospitalDAOimpl.updateHospital(hospital);
        verify(hospitalDAOimpl, atLeastOnce()).updateHospital(hospital);
    }
    @Test(expected = RuntimeException.class)
    public void testGetHospital(){
        Assert.assertNotNull(hospitalDAO);
        when(hospitalDAO.getHospital(1)).thenReturn(hospital);
        when(hospitalDAOimpl.getHospital(1)).thenReturn(hospital);
        verify(hospitalDAOimpl, atLeastOnce()).getHospital(1);
    }
    @Test(expected = RuntimeException.class)
    public void testGetAll(){
        Assert.assertNotNull(hospitalDAO);
        when(hospitalDAO.getAllHospitals()).thenReturn(hospitals);
        when(hospitalDAOimpl.getAllHospitals()).thenReturn(hospitals);
        verify(hospitalDAOimpl, atLeastOnce()).getAllHospitals();
        verify(hospitalDAOimpl,atLeastOnce()).getAllHospitals();
    }
}
