package com.example.controller;

import com.example.modal.Patient;
import com.example.modal.PatientAppointment;
import com.example.service.AppointmentService;
import com.example.service.DoctorService;
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
public class AppointmentController {
    int doctorId;
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    DoctorService doctorService;
    @Autowired
    PatientService patientService;
    @RequestMapping(value = "/createAppointment",method = RequestMethod.GET)
    public ModelAndView createAppointment(ModelAndView model,HttpServletRequest request){
        return new ModelAndView("index");
    }
/*    @RequestMapping(value = "fixAppointment", method = RequestMethod.POST)
    public ModelAndView saveDoctor(@ModelAttribute PatientAppointment patientAppointment,HttpServletRequest request){
        doctorId=Integer.parseInt(request.getParameter("doctorId"));
        patientAppointment.setDoctor(doctorService.getDoctor(doctorId));
        String [] items = request.getParameterValues("patients");
        List<Patient> patients= patientService.getAllPatients();

        return new ModelAndView("viewDoctorAppointments","patients",patients);
    }*/
    @RequestMapping(value = "/viewMyPatients",method = RequestMethod.GET)
    public ModelAndView saveDoctorAppointment(HttpServletRequest request,ModelAndView modal){
        List<Patient> patients = patientService.getAllPatients();
        int doctorId=Integer.parseInt(request.getParameter("id"));
        List<PatientAppointment> appointments=doctorService.getDoctor(doctorId).getAppointments();
        modal.addObject("appointments",appointments);
        modal.addObject("doctorId",doctorId);
        modal.setViewName("viewDoctorAppointments");
        return  modal;
    }
   /* @RequestMapping(value = "/deleteAppointment",method = RequestMethod.GET)
    public ModelAndView deleteDoctorAppointment(HttpServletRequest request, ModelAndView model){

    }*/

}
