package com.example.controller;

import com.example.modal.Doctor;
import com.example.modal.Patient;
import com.example.modal.PatientAppointment;
import com.example.service.AppointmentService;
import com.example.service.DoctorService;
import com.example.service.HospitalService;
import com.example.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class PatientController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        System.out.println("init Builder");
        SimpleDateFormat sdf = new SimpleDateFormat("MM/DD/YYYY");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
    @Autowired
    PatientService patientService;

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    HospitalService hospitalService;

    @Autowired
    DoctorService doctorService;

    int appointmentId;

    @RequestMapping(value ="/addPatient",method = RequestMethod.GET)
    public ModelAndView fixAppointment(ModelAndView model){
        List<Doctor> doctors=doctorService.getAllDoctors();
        model.addObject("doctors",doctors);
        model.addObject("patient",new Patient());
        model.setViewName("patientForm");
        return model;
    }
    @RequestMapping(value = "/savePatient", method = RequestMethod.POST)
    public ModelAndView addPatient(@ModelAttribute Patient patient, ModelAndView model,HttpServletRequest request){
        System.out.println("Patient---> "+patient.getAppointment().getDate());
        patient.getAppointment().setTime(patient.getAppointment().getTime().toString());
        patientService.addPatient(patient);
        List<Patient> patients = patientService.getAllPatients();
        System.out.println("Doctor---> "+patients.get(0).getAppointment().getDoctor().getFirstName());

        return new ModelAndView("index","totalData",patients);

    }
}

