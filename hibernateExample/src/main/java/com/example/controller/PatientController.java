package com.example.controller;

import com.example.modal.Patient;
import com.example.modal.PatientAppointment;
import com.example.service.AppointmentService;
import com.example.service.DoctorService;
import com.example.service.HospitalService;
import com.example.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PatientController {

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
    public ModelAndView fixAppointment(){
        List<PatientAppointment> appointments=appointmentService.getAllPatientAppointments();
        return new ModelAndView("patientForm","appointments",appointments);
    }
    @RequestMapping(value = "/savePatient", method = RequestMethod.GET)
    public ModelAndView addPatient( ModelAndView model,HttpServletRequest request){
        Patient patient= new Patient();
        patient.setFirstName(request.getParameter("firstName"));
        patient.setLastname(request.getParameter("lastName"));
        patient.setAge(Integer.parseInt(request.getParameter("age")));
        patient.setGender(request.getParameter("gender"));
      appointmentId=Integer.parseInt(request.getParameter("appointmentId"));
        System.out.println("AppointmentId---> "+appointmentId);

        PatientAppointment appointment = (PatientAppointment) appointmentService.getPatientAppointment(appointmentId);
        System.out.println("ControllerAppointment---> "+appointment);
        patient.setAppointment(appointment);
        patientService.addPatient(patient);
        return new ModelAndView("/index");
    }
}

