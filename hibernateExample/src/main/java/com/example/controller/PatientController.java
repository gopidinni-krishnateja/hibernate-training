package com.example.controller;

import com.example.modal.Doctor;
import com.example.modal.Hospital;
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
import java.util.HashMap;
import java.util.Iterator;
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
        patient.getAppointment().setTime(patient.getAppointment().getTime().toString());
        if (patient.getId() == 0) {
            patientService.addPatient(patient);
        } else {
            patientService.updatePatient(patient);
        }
        patientService.addPatient(patient);
        List<Hospital> hospitals = hospitalService.getAllHospitals();
     //   System.out.println("hospitals---> "+hospitals.get(0).getDoctors().get(0).getFirstName());
        return new ModelAndView("index","hospitals",hospitals);
    }
    @RequestMapping(value = "/viewPatients", method = RequestMethod.GET)
    public ModelAndView viewPatients(ModelAndView model, HttpServletRequest request){
        List<Patient> patients = patientService.getAllPatients();
        return new ModelAndView("viewPatients","totalData",patients);
    }
    @RequestMapping(value = "editPatient",method = RequestMethod.GET)
    public ModelAndView editPatient(ModelAndView model,HttpServletRequest request){
        int patientId=Integer.parseInt(request.getParameter("id"));
        Patient patient=patientService.getPatient(patientId);
        model.addObject("patient",patient);
        model.addObject("patientId",patientId);
        List<Doctor> doctors=doctorService.getAllDoctors();
        model.addObject("doctors",doctors);
        model.setViewName("patientForm");
        return model;
    }
    @RequestMapping(value = "/deletePatient",method = RequestMethod.GET)
    public ModelAndView deleteDoctor(ModelAndView model,HttpServletRequest request){
        int patientId=Integer.parseInt(request.getParameter("id"));
        System.out.println("PatientId---->"+patientId);
        patientService.deletePatient(patientId);
        List<Hospital> hospitals = hospitalService.getAllHospitals();
        //System.out.println("hospitals---> "+hospitals.get(0).getDoctors().get(0).getFirstName());
        return new ModelAndView("index","hospitals",hospitals);
    }
}

