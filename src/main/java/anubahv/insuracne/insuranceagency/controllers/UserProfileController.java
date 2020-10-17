package anubahv.insuracne.insuranceagency.controllers;

import anubahv.insuracne.insuranceagency.models.Property;
import anubahv.insuracne.insuranceagency.models.User;
import anubahv.insuracne.insuranceagency.models.Vehicle;
import anubahv.insuracne.insuranceagency.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    StorageService storageService;

    @Autowired
    public UserProfileController(UserService userService, SecurityService securityService, PropertyService propertyService, VehicleService vehicleService, PolicyRecordService policyRecordService, PropertyClaimsServices propertyClaimsServices, VehicleClaimsService vehicleClaimsService, HealthClaimServices healthClaimServices, LifeInsuranceClaimService lifeInsuranceClaimService, StorageService storageService) {
        this.userService = userService;
        this.securityService = securityService;
        this.propertyService = propertyService;
        this.vehicleService = vehicleService;
        this.policyRecordService = policyRecordService;
        this.propertyClaimsServices = propertyClaimsServices;
        this.vehicleClaimsService = vehicleClaimsService;
        this.healthClaimServices = healthClaimServices;
        this.lifeInsuranceClaimService = lifeInsuranceClaimService;
        this.storageService = storageService;
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

    @GetMapping({"/self/property/add"})
    public String addProperty(Model model){
        String loggedInUserName = securityService.findLoggedInUsername();
        if(loggedInUserName==null){
            return "redirect:/login";
        }
        model.addAttribute("property",new Property());
        return "profile/addProperty";
    }

    @PostMapping("/self/property/add")
    public String addProperty(@RequestParam("file")MultipartFile file,@ModelAttribute Property property,Model model){
        String loggedInUserName = securityService.findLoggedInUsername();
        if(loggedInUserName==null){
            return "redirect:/login";
        }
        if(file.isEmpty() || property.getName()==null){
            model.addAttribute("link","/self/property/add");
            return "profile/formFailure";
        }
        storageService.uploadFile(file,loggedInUserName,"property/"+property.getName());
        User user = userService.findByUsername(loggedInUserName);
        property.setUserId(user.getId());
        property.setDocumentLocation(storageService.getUploadLocation(file,loggedInUserName,"property/"+property.getName()));
        propertyService.addProperty(property);
        return "redirect:/self/property";
    }

    @RequestMapping("/self/vehicle")
    public String vehiclePage(Model model){
        String loggedInUserName = securityService.findLoggedInUsername();
        if(loggedInUserName==null){
            return "redirect:/login";
        }
        User user = userService.findByUsername(loggedInUserName);
        List<Vehicle> vehicles = vehicleService.getByUser(user.getId());
        model.addAttribute("vehicles",vehicles);
        return "profile/vehicles";
    }

    @GetMapping("/self/vehicle/add")
    public String addVehicle(Model model){
        String loggedInUserName = securityService.findLoggedInUsername();
        if(loggedInUserName==null){
            return "redirect:/login";
        }
        model.addAttribute("vehicle",new Vehicle());
        return "profile/addVehicle";
    }

    @PostMapping("/self/vehicle/add")
    public String addVehicle(@RequestParam("file")MultipartFile file,@ModelAttribute("vehicle")Vehicle vehicle,Model model){
        String loggedInUserName = securityService.findLoggedInUsername();
        if(loggedInUserName==null){
            return "redirect:/login";
        }
        if(file.isEmpty() || vehicle.getVehicleNumber()==null){
            model.addAttribute("link","/self/vehicle/add");
            return "formFailure";
        }
        storageService.uploadFile(file,loggedInUserName,"vehicle/"+vehicle.getVehicleNumber());
        User user = userService.findByUsername(loggedInUserName);
        vehicle.setUserId(user.getId());
        vehicle.setDocumentLocation(storageService.getUploadLocation(file,loggedInUserName,"vehilce/"+vehicle.getVehicleNumber()));
        vehicleService.addVehicle(vehicle);
        return "redirect:/self/vehicle";
    }

    @RequestMapping("/self/policies")
    public String policyRecords(Model model){
        String loggedInUserName = securityService.findLoggedInUsername();
        if(loggedInUserName==null){
            return "redirect:/login";
        }
        User user = userService.findByUsername(loggedInUserName);
        model.addAttribute("policies",policyRecordService.getAllOfUser(user.getId()));
        return "profile/policyRecords";
    }
}
