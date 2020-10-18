package anubahv.insuracne.insuranceagency.controllers;

import anubahv.insuracne.insuranceagency.models.*;
import anubahv.insuracne.insuranceagency.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ClaimsController {
    VehicleClaimsService vehicleClaimsService;
    PropertyClaimsServices propertyClaimsServices;
    HealthClaimServices healthClaimServices;
    LifeInsuranceClaimService lifeInsuranceClaimService;
    SecurityService securityService;
    UserService userService;
    PolicyRecordService policyRecordService;
    PolicyService policyService;
    StorageService storageService;
    VehicleService vehicleService;
    PropertyService propertyService;

    @Autowired
    public ClaimsController(VehicleClaimsService vehicleClaimsService, PropertyClaimsServices propertyClaimsServices, HealthClaimServices healthClaimServices, LifeInsuranceClaimService lifeInsuranceClaimService, SecurityService securityService, UserService userService, PolicyRecordService policyRecordService, PolicyService policyService, StorageService storageService, VehicleService vehicleService, PropertyService propertyService) {
        this.vehicleClaimsService = vehicleClaimsService;
        this.propertyClaimsServices = propertyClaimsServices;
        this.healthClaimServices = healthClaimServices;
        this.lifeInsuranceClaimService = lifeInsuranceClaimService;
        this.securityService = securityService;
        this.userService = userService;
        this.policyRecordService = policyRecordService;
        this.policyService = policyService;
        this.storageService = storageService;
        this.vehicleService = vehicleService;
        this.propertyService = propertyService;
    }

    @GetMapping("/claims/health/{id}")
    public String healthClaim(@PathVariable("id")int id, Model model){
        String loggedInUserName = securityService.findLoggedInUsername();
        if(loggedInUserName==null){
            return "redirect:/login";
        }
        HealthClaim healthClaim = new HealthClaim();
        healthClaim.setRecordId(id);
        model.addAttribute("healthClaim",healthClaim);
        return "claims/healthClaim";
    }

    @PostMapping("/claims/health/{id}")
    public String healthClaim(@PathVariable("id")int id, @ModelAttribute("healthClaim") HealthClaim healthClaim, Model model,@RequestParam("file") MultipartFile file,@RequestParam("date") String date){
        String loggedInUserName = securityService.findLoggedInUsername();
        if(loggedInUserName==null){
            return "redirect:/login";
        }
        if(file.isEmpty() || healthClaim.getDamage()==0 || date==null){
            model.addAttribute("link","/claims/health/"+id);
            return "profile/formFailure";
        }
        User user = userService.findByUsername(loggedInUserName);
        PolicyRecord policyRecord = policyRecordService.getPolicyRecord(id);
        if(policyRecord.getUserId()!=user.getId()){
            model.addAttribute("link","/claims/health/"+id);
            return "profile/formFailure";
        }
        try {
            healthClaim.setDateOfLoss(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
            model.addAttribute("link","/claims/health/"+id);
            return "profile/formFailure";
        }
        Policy policy = policyService.findById(policyRecord.getPolicyId());
        if(!policy.getCategory().equals("health")){
            model.addAttribute("link","/self");
            return "profile/formFailure";
        }
        storageService.uploadFile(file,loggedInUserName,"claims/health/"+id);
        healthClaim.setAmount(policy.getMaxClaimAmount());
        healthClaim.setStatus("active");
        System.out.println(date);
        healthClaim.setRecordId(policyRecord.getId());
        System.out.println(healthClaim.getRecordId());
        List<String> docs = new ArrayList<>();
        docs.add(storageService.getUploadLocation(file,loggedInUserName,"claims/health/"+id));
        healthClaim.setLinkToDocuments(docs);
        healthClaimServices.add(healthClaim);
        return "redirect:/self";
    }

    @GetMapping("/claims/vehicle/{id}")
    public String vehicleClaim(@PathVariable("id")int id, Model model){
        String loggedInUserName = securityService.findLoggedInUsername();
        if(loggedInUserName==null){
            return "redirect:/login";
        }
        VehicleClaims vehicleClaims = new VehicleClaims();
        vehicleClaims.setRecordId(id);
        model.addAttribute("vehicleClaim",vehicleClaims);
        return "claims/vehicleClaim";
    }

    @PostMapping("/claims/vehicle/{id}")
    public String vehicleClaim(@PathVariable("id")int id,Model model, @RequestParam("file")MultipartFile file,@RequestParam("date") String date,@ModelAttribute("vehicleClaim")VehicleClaims vehicleClaims){
        String loggedInUserName = securityService.findLoggedInUsername();
        if(loggedInUserName==null){
            return "redirect:/login";
        }
        User user = userService.findByUsername(loggedInUserName);
        PolicyRecord policyRecord = policyRecordService.getPolicyRecord(id);
        if(policyRecord.getUserId()!=user.getId()){
            model.addAttribute("link","/claims/vehicle/"+id);
            return "profile/formFailure";
        }
        try{
            vehicleClaims.setDateOfLoss(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        }catch (ParseException e){
            e.printStackTrace();
            model.addAttribute("link","/claims/vehicle/"+id);
            return "profile/formFailure";
        }
        Policy policy = policyService.findById(policyRecord.getPolicyId());
        if(!policy.getCategory().equals("vehicle")){
            model.addAttribute("link","/self");
            return "profile/formFailure";
        }
        try{
            Vehicle vehicle = vehicleService.getByRecord(policyRecord.getId());
            vehicleClaims.setVehicleId(vehicle.getId());
        }catch (Exception e){
            //cannot find a valid vehicle cannot claim;
            return "profile/formFailure";
        }
        storageService.uploadFile(file,loggedInUserName,"claims/vehicle/"+id);
        vehicleClaims.setAmount(policy.getMaxClaimAmount());
        vehicleClaims.setRecordId(policyRecord.getId());
        vehicleClaims.setStatus("active");
        vehicleClaimsService.add(vehicleClaims);
        List<String> docs = new ArrayList<>();
        docs.add(storageService.getUploadLocation(file,loggedInUserName,"claims/vehicle/"+id));
        return "redirect:/self";
    }
}
