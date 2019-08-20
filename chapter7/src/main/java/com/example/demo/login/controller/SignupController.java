package com.example.demo.login.controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.GroupOrder;
import com.example.demo.login.domain.model.SignupForm;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class SignupController {
    // Make radioButton
    private Map<String, String> radioMarriage;
    private Map<String, String> initRadioMarriage(){
        Map<String, String> radio = new LinkedHashMap<>();
        radio.put("Married", "true");
        radio.put("Unmarried", "false");
        return radio;
    }
    // Set radioButton to Model object
    @GetMapping("/signup")
    public String getSignUp(@ModelAttribute SignupForm form, Model model){
        radioMarriage = initRadioMarriage();
        model.addAttribute("radioMarriage", radioMarriage);
        return "login/signup";
    }
    // Redirect to login.html
    @PostMapping("/signup")
    public String postSignUp(@ModelAttribute @Validated(GroupOrder.class) SignupForm form, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return getSignUp(form, model);
        }
        System.out.println(form);
        return "redirect:/login";
    }
}
