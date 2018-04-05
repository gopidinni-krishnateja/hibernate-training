package com.example.controller;

import com.example.modal.Doctor;
import com.example.modal.Hospital;
import com.example.modal.Patient;
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
public class HospitalController {



    public HospitalController() {
        System.out.println("HospitalController()");
    }

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    PatientService patientService;
    @Autowired
    private DoctorService doctorService;
    @RequestMapping(value = "/")
    public ModelAndView homePage()
    {
        List<Patient> patients = patientService.getAllPatients();
        return new ModelAndView("index","totalData",patients);
    }
    @RequestMapping(value = "/newHospital", method = RequestMethod.GET)
    public ModelAndView newHospital(ModelAndView model) {
        model.setViewName("hospital");
        return model;
    }
    @RequestMapping(value = "/saveHospital", method = RequestMethod.POST)
    public ModelAndView saveEmployee(@ModelAttribute Hospital hospital) {
        if (hospital.getId() == 0) {
            hospitalService.addHospital(hospital);
        } else {
            hospitalService.updateHospital(hospital);
        }
        List<Hospital> hospitals = hospitalService.getAllHospitals();
        return new ModelAndView("viewhospitals","hospitals",hospitals);
    }
    @RequestMapping(value = "/deleteHospital",method = RequestMethod.GET)
    public ModelAndView deleteDoctor(ModelAndView model,HttpServletRequest request){
        int hospitalId=Integer.parseInt(request.getParameter("id"));
        System.out.println("HospitalId---->"+hospitalId);
        hospitalService.deleteHospital(hospitalId);
        List<Patient> patients = patientService.getAllPatients();
        return new ModelAndView("index","totalData",patients);
    }
}
