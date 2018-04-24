package com.example.controller;

import com.example.modal.Doctor;
import com.example.modal.Hospital;
import com.example.modal.Patient;
import com.example.modal.PatientAppointment;
import com.example.service.DoctorService;
import com.example.service.HospitalService;
import com.example.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

@Controller
public class DoctorController {

    @Autowired
    private HospitalService hospitalService;


    @Autowired
    private DoctorService doctorService;
    int hospitalId;

    @Autowired
    private PatientService patientService;
    @RequestMapping(value = "/newDoctor", method = RequestMethod.GET)
    public ModelAndView newDoctor(ModelAndView model,HttpServletRequest request) {
        model.addObject("doctor",new Doctor());
        model.setViewName("addDoctor");
        return model;
    }

    @RequestMapping(value ="/saveDoctor",method = RequestMethod.POST)
    public  ModelAndView addDoctor(@Valid @ModelAttribute("doctor") Doctor doctor, BindingResult result, ModelAndView model){
        //doctorValidator.validate(doctor,result);
        if(result.hasErrors()){
            //model.addObject("doctor",new Doctor());
              model.setViewName("addDoctor");
        } else {
            int hospital_id=doctor.getHospital().getId();
            Hospital hospital = hospitalService.getHospital(hospital_id);
            doctor.setHospital(hospital);

            if (doctor.getId() == 0) {
                doctorService.addDoctor(doctor);
            } else {
                doctorService.updateDoctor(doctor);
            }
            List<Hospital> hospitals = hospitalService.getAllHospitals();
            model.addObject("hospitals",hospitals);
            model.setViewName("/index");
            // System.out.println("hospitals---> "+hospitals.get(0).getDoctors().get(0).getFirstName());
        }
        return model;
    }
    @RequestMapping(value ="/viewAllDoctors",method = RequestMethod.GET)
    public  ModelAndView viewAllDoctors(ModelAndView model,HttpServletRequest request){
        List<Doctor> doctors=doctorService.getUnAssignedDoctors();
        int hospitalId=Integer.parseInt(request.getParameter("hospitalId"));
        Hospital hospital=hospitalService.getHospital(hospitalId);
        if(doctors.size()==0){
            model.setViewName("noDoctorsFound");
        } else {
            model.addObject("hospitalName",hospital.getName());
            model.addObject("hospitalId",hospitalId);
            model.addObject("doctors",doctors);
            model.setViewName("viewAllDoctors");
        }

        return model;
    }

    @RequestMapping(value = "/deleteDoctor",method = RequestMethod.GET)
    public ModelAndView deleteDoctor(ModelAndView model,HttpServletRequest request){
        int doctorId=Integer.parseInt(request.getParameter("id"));
        Doctor doctor=doctorService.getDoctor(doctorId);
        if(doctor.getAppointments()!=null) {
            List<PatientAppointment> appointments = doctor.getAppointments();
            for (int i = 0; i < appointments.size(); i++) {
                if (appointments.get(i).getPatient() != null) {
                    patientService.deletePatient(appointments.get(i).getPatient().getId());
                }
            }
            System.out.println("Patients Deleted Success--->");
        }
        doctorService.deleteDoctor(doctorId);
        System.out.println("Doctor Deleted Success--->");

        List<Hospital> hospitals = hospitalService.getAllHospitals();
       // System.out.println("hospitals---> "+hospitals.get(0).getDoctors().get(0).getFirstName());
        return new ModelAndView("/index","hospitals",hospitals);
    }
    @RequestMapping(value = "/viewDoctors", method = RequestMethod.GET)
    public ModelAndView viewDoctors(ModelAndView model,HttpServletRequest request){
        int hospitalId=Integer.parseInt(request.getParameter("id"));
        model.addObject("hospitalId",hospitalId);
        List<Doctor> doctors=hospitalService.getHospital(hospitalId).getDoctors();
        model.addObject("doctors",doctors);
        model.setViewName("viewDoctors");
        return model;
    }
    @RequestMapping(value = "editDoctor",method = RequestMethod.GET)
    public ModelAndView editDoctor(ModelAndView model, HttpServletRequest request){
        int doctorId=Integer.parseInt(request.getParameter("id"));
        Doctor doctor=doctorService.getDoctor(doctorId);
        model.addObject("doctor",doctor);
        model.addObject("doctorId",doctorId);
        model.setViewName("addDoctor");
        return model;
    }
    @RequestMapping(value="searchDoctor",method = RequestMethod.GET)
    public ModelAndView searchDoctor(ModelAndView model,HttpServletRequest request){
        String firstName=request.getParameter("firstName");
        Integer hospitalId=Integer.parseInt(request.getParameter("hospitalId"));
        Hospital hospital=hospitalService.getHospital(hospitalId);
        List<Doctor> doctors=doctorService.searchDoctor(firstName,hospital);
        model.addObject("hospitalId",hospitalId);
        model.addObject("doctors",doctors);
        model.setViewName("viewDoctors");
        return model;

    }

}
