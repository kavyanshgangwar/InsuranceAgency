package anubahv.insuracne.insuranceagency.controllers;

import anubahv.insuracne.insuranceagency.services.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/policy"})
public class PolicyController {

    PolicyService policyService;

    @Autowired
    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @RequestMapping({"","/"})
    public String policyCategories(Model model){
        return "policy/policyCategories";
    }

    @RequestMapping({"/health"})
    public String healthPolicies(Model model){
        model.addAttribute("category","health");
        model.addAttribute("policies",policyService.findActiveHealthPolicies());
        return "policy/policyListing";
    }

    @RequestMapping({"/property"})
    public String propertyPolicies(Model model){
        model.addAttribute("category","property");
        model.addAttribute("policies",policyService.findActivePropertyPolicies());
        return "policy/policyListing";
    }

    @RequestMapping({"/vehicle"})
    public String vehiclePolicies(Model model){
        model.addAttribute("category","vehicle");
        model.addAttribute("policies",policyService.findActiveVehiclePolicies());
        return "policy/policyListing";
    }
    @RequestMapping({"/life"})
    public String lifePolicies(Model model){
        model.addAttribute("category","life");
        model.addAttribute("policies",policyService.findActiveLifePolicies());
        return "policy/policyListing";
    }

    @RequestMapping({"/{id}"})
    public String policyDetails(@PathVariable("id") int id, Model model){
        model.addAttribute("policy",policyService.findById(id));
        return "policy/policyDetails";
    }
}
