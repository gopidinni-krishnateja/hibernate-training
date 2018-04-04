package com.example.controller;

import com.example.modal.Doctor;
import com.example.modal.Hospital;
import com.example.service.DoctorService;
import com.example.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HospitalController {



    public HospitalController() {
        System.out.println("HospitalController()");
    }

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private DoctorService doctorService;
    @RequestMapping(value = "/")
    public ModelAndView homePage(){
        return new ModelAndView("index");
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
}
