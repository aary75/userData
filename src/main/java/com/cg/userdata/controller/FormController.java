package com.cg.userdata.controller;

import org.springframework.stereotype.Controller;

import com.cg.userdata.model.userFormData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//import javax.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/form")
public class FormController {

    @GetMapping("/page1")
    public String showPage1(Model model) {
        model.addAttribute("userFormData", new userFormData());
        return "page1";
    }

    @PostMapping("/page1")
    public String handlePage1(@ModelAttribute("userFormData") userFormData formData, HttpSession session) {
        session.setAttribute("userFormData", formData);
        return "redirect:/form/page2";
    }

    @GetMapping("/page2")
    public String showPage2(HttpSession session, Model model) {
        userFormData formData = (userFormData) session.getAttribute("userFormData");
        model.addAttribute("userFormData", formData);
        return "page2";
    }

    @PostMapping("/page2")
    public String handlePage2(@ModelAttribute("userFormData") userFormData formData, HttpSession session) {
        userFormData storedForm = (userFormData) session.getAttribute("userFormData");
        storedForm.setEmail(formData.getEmail());
        storedForm.setPhone(formData.getPhone());
        session.setAttribute("userFormData", storedForm);
        return "redirect:/form/page3";
    }

    @GetMapping("/page3")
    public String showPage3(HttpSession session, Model model) {
        userFormData formData = (userFormData) session.getAttribute("userFormData");
        model.addAttribute("userFormData", formData);
        return "page3";
    }

    @PostMapping("/page3")
    public String handlePage3(@ModelAttribute("userFormData") userFormData formData, HttpSession session) {
        userFormData storedForm = (userFormData) session.getAttribute("userFormData");
        storedForm.setCity(formData.getCity());
        storedForm.setCountry(formData.getCountry());
        session.setAttribute("userFormData", storedForm);
        return "redirect:/form/page4";
    }

    @GetMapping("/page4")
    public String showPage4(HttpSession session, Model model) {
        userFormData formData = (userFormData) session.getAttribute("userFormData");
        model.addAttribute("userFormData", formData);
        return "page4";
    }

    @PostMapping("/submit")
    public String submitForm(HttpSession session) {
        userFormData formData = (userFormData) session.getAttribute("userFormData");

        // Save to database or print
        System.out.println("Submitted Data:");
        System.out.println(formData.getFirstName() + " " + formData.getLastName());
        System.out.println(formData.getEmail() + ", " + formData.getPhone());
        System.out.println(formData.getCity() + ", " + formData.getCountry());

        session.removeAttribute("userFormData"); // Clear session
        return "redirect:/form/page1"; // After submission, go back to start
    }
}
