package anubahv.insuracne.insuranceagency.controllers;

import anubahv.insuracne.insuranceagency.models.Policy;
import anubahv.insuracne.insuranceagency.models.User;
import anubahv.insuracne.insuranceagency.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin")
@Controller
public class AdminController {
    PolicyService policyService;
    SecurityService securityService;
    HealthClaimServices healthClaimServices;
    VehicleClaimsService vehicleClaimsService;
    PropertyClaimsServices propertyClaimsServices;
    LifeInsuranceClaimService lifeInsuranceClaimService;

    @Autowired
    public AdminController(PolicyService policyService, SecurityService securityService, HealthClaimServices healthClaimServices, VehicleClaimsService vehicleClaimsService, PropertyClaimsServices propertyClaimsServices, LifeInsuranceClaimService lifeInsuranceClaimService) {
        this.policyService = policyService;
        this.securityService = securityService;
        this.healthClaimServices = healthClaimServices;
        this.vehicleClaimsService = vehicleClaimsService;
        this.propertyClaimsServices = propertyClaimsServices;
        this.lifeInsuranceClaimService = lifeInsuranceClaimService;
    }




    @RequestMapping({"","/"})
    public String adminHome(){
        String loggedInUserName = securityService.findLoggedInUsername();
        if(loggedInUserName==null){
            return "redirect:/login";
        }

        return "admin/adminHome";
    }

    @GetMapping({"/policy/add"})
    public String addPolicy(Model model){
        model.addAttribute("policy",new Policy());
        return "admin/addPolicy";
    }

    @PostMapping("/policy/add")
    public String addPolicy(@ModelAttribute("policy")Policy policy, Model model){
        policyService.addPolicy(policy);
        return "redirect:/admin";
    }

    @GetMapping("/policy/change")
    public String changeStatusOfPolicy(Model model){
        model.addAttribute("policies",policyService.findAll());
        return "admin/changeStatus";
    }

    @PostMapping("/policy/change")
    public String changeStatusOfPolicy(@RequestParam("status")String status,@RequestParam("id") int id){
        policyService.changeExpirationStatus(status,id);
        return "redirect:/admin";
    }

    @RequestMapping("/claims")
    public String seeAllClaims(Model model){
        model.addAttribute("healthClaims",healthClaimServices.getClaimsByStatus("active"));
        model.addAttribute("vehicleClaims",vehicleClaimsService.getClaimsByStatus("active"));
        model.addAttribute("propertyClaims",propertyClaimsServices.getClaimsByStatus("active"));
        model.addAttribute("lifeClaims",lifeInsuranceClaimService.getClaimsByStatus("active"));
        return "admin/claims";
    }
}
