package anubahv.insuracne.insuranceagency.controllers;

import anubahv.insuracne.insuranceagency.models.Property;
import anubahv.insuracne.insuranceagency.models.User;
import anubahv.insuracne.insuranceagency.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserProfileController {
    UserService userService;
    SecurityService securityService;
    PropertyService propertyService;
    VehicleService vehicleService;
    PolicyRecordService policyRecordService;
    PropertyClaimsServices propertyClaimsServices;
    VehicleClaimsService vehicleClaimsService;
    HealthClaimServices healthClaimServices;
    LifeInsuranceClaimService lifeInsuranceClaimService;

    @Autowired
    public UserProfileController(UserService userService, SecurityService securityService, PropertyService propertyService, VehicleService vehicleService, PolicyRecordService policyRecordService, PropertyClaimsServices propertyClaimsServices, VehicleClaimsService vehicleClaimsService, HealthClaimServices healthClaimServices, LifeInsuranceClaimService lifeInsuranceClaimService) {
        this.userService = userService;
        this.securityService = securityService;
        this.propertyService = propertyService;
        this.vehicleService = vehicleService;
        this.policyRecordService = policyRecordService;
        this.propertyClaimsServices = propertyClaimsServices;
        this.vehicleClaimsService = vehicleClaimsService;
        this.healthClaimServices = healthClaimServices;
        this.lifeInsuranceClaimService = lifeInsuranceClaimService;
    }

    @RequestMapping("/self")
    public String profilePage(Model model){
        String loggedInUserName = securityService.findLoggedInUsername();
        if(loggedInUserName==null){
            return "redirect:/login";
        }
        User user =  userService.findByUsername(loggedInUserName);
        model.addAttribute("user",user);
        return "profile/userprofile";
    }

    @RequestMapping("/self/property")
    public String propertyPage(Model model){
        String loggedInUserName = securityService.findLoggedInUsername();
        if(loggedInUserName==null){
            return "redirect:/login";
        }
        User user = userService.findByUsername(loggedInUserName);
        List<Property> properties = propertyService.getByUser(user.getId());
        model.addAttribute("properties",properties);
        return "profile/property";
    }
}
