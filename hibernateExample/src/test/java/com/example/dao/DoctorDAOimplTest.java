package com.example.dao;

import com.example.modal.Doctor;
import com.example.modal.Hospital;
import com.example.modal.PatientAppointment;
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

public class DoctorDAOimplTest {
    @Mock
    DoctorDAO doctorDAO;
    @InjectMocks
    DoctorDAOimpl doctorDAOimpl;
    @Spy
    List<Doctor> doctors = new ArrayList<Doctor>();
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        doctors = getDoctorList();
    }
    Hospital hospital=new Hospital();
    Doctor doctor=new Doctor();
    public List<Doctor> getDoctorList(){
        Hospital hospital=new Hospital();
        Doctor doctor=new Doctor();
        hospital.setId(1);
        hospital.setName("CARE");
        hospital.setCityName("Hyderabad");
        doctor.setId(1);
        doctor.setFirstName("Krishna");
        doctor.setLastName("Teja");
        doctor.setType("ENT");
        doctor.setHospital(hospital);
        Doctor doctor1=new Doctor();
        doctor1.setId(2);
        doctor1.setFirstName("Rama");
        doctor1.setLastName("Krishna");
        doctor1.setType("SKIN");
        doctor1.setHospital(hospital);
        doctors.add(doctor);
        doctors.add(doctor1);
        return doctors;
    }
    @Test(expected = RuntimeException.class)
    public void testAddDoctor(){
        Assert.assertNotNull(doctorDAO);
        doThrow(RuntimeException.class).when(doctorDAO).addDoctor(doctor);
        doctorDAOimpl.addDoctor(doctor);
        verify(doctorDAOimpl, atLeastOnce()).addDoctor(doctor);
    }
    @Test(expected = RuntimeException.class)
    public void testDeleteDoctor(){
        Assert.assertNotNull(doctorDAO);
        doThrow(RuntimeException.class).when(doctorDAO).deleteDoctor(1);
        doctorDAOimpl.deleteDoctor(1);
        verify(doctorDAOimpl, atLeastOnce()).deleteDoctor(1);
    }
    @Test(expected = RuntimeException.class)
    public void testUpdateDoctor(){
        Assert.assertNotNull(doctorDAO);
        doThrow(RuntimeException.class).when(doctorDAO).updateDoctor(doctor);
        doctorDAOimpl.updateDoctor(doctor);
        verify(doctorDAOimpl, atLeastOnce()).updateDoctor(doctor);
    }
    @Test(expected = RuntimeException.class)
    public void testGetDoctor(){
        Assert.assertNotNull(doctorDAO);
        when(doctorDAO.getDoctor(1)).thenReturn(doctor);
        when(doctorDAOimpl.getDoctor(1)).thenReturn(doctor);
        verify(doctorDAOimpl, atLeastOnce()).getDoctor(1);
    }
    @Test(expected = RuntimeException.class)
    public void testGetAll(){
        Assert.assertNotNull(doctorDAO);
        when(doctorDAO.getAll()).thenReturn(doctors);
        when(doctorDAOimpl.getAll()).thenReturn(doctors);
        verify(doctorDAOimpl, atLeastOnce()).getAll();
        verify(doctorDAO,atLeastOnce()).getAll();
    }
    @Test(expected = RuntimeException.class)
    public void testUnAssignedDoctors(){
        Assert.assertNotNull(doctorDAO);
        when(doctorDAO.getUnAssigned()).thenReturn(doctors);
        when(doctorDAOimpl.getUnAssigned()).thenReturn(doctors);
        verify(doctorDAOimpl, atLeastOnce()).getUnAssigned();
        verify(doctorDAO,atLeastOnce()).getUnAssigned();
    }
    @Test(expected = RuntimeException.class)
    public void testSearchDoctor(){
        Assert.assertNotNull(doctorDAO);
        when(doctorDAO.searchDoctor("Krishna",hospital)).thenReturn(doctors);
        when(doctorDAOimpl.searchDoctor("Krishna",hospital)).thenReturn(doctors);
        verify(doctorDAOimpl, atLeastOnce()).searchDoctor("Krishna",hospital);
    }

}
