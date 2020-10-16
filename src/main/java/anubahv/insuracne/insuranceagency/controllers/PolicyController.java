package anubahv.insuracne.insuranceagency.controllers;

import anubahv.insuracne.insuranceagency.models.Policy;
import anubahv.insuracne.insuranceagency.repository.FAQRepository;
import anubahv.insuracne.insuranceagency.services.PolicyService;
import anubahv.insuracne.insuranceagency.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/policy"})
public class PolicyController {

    PolicyService policyService;
    SecurityService securityService;
    FAQRepository faqRepository;

    @Autowired
    public PolicyController(PolicyService policyService,SecurityService securityService,FAQRepository faqRepository) {
        this.faqRepository = faqRepository;
        this.policyService = policyService;
        this.securityService = securityService;
    }

    @RequestMapping({"","/"})
    public String policyCategories(Model model){
        return "policy/policyCategories";
    }

    @RequestMapping({"/health"})
    public String healthPolicies(Model model){
        model.addAttribute("category","health");
        model.addAttribute("policies",policyService.findActiveHealthPolicies());
        model.addAttribute("faqs",faqRepository.findByTopic("health"));
        return "policy/policyListing";
    }

    @RequestMapping({"/property"})
    public String propertyPolicies(Model model){
        model.addAttribute("category","property");
        model.addAttribute("policies",policyService.findActivePropertyPolicies());
        model.addAttribute("faqs",faqRepository.findByTopic("property"));
        return "policy/policyListing";
    }

    @RequestMapping({"/vehicle"})
    public String vehiclePolicies(Model model){
        model.addAttribute("category","vehicle");
        model.addAttribute("policies",policyService.findActiveVehiclePolicies());
        model.addAttribute("faqs",faqRepository.findByTopic("vehicle"));
        return "policy/policyListing";
    }
    @RequestMapping({"/life"})
    public String lifePolicies(Model model){
        model.addAttribute("category","life");
        model.addAttribute("policies",policyService.findActiveLifePolicies());
        model.addAttribute("faqs",faqRepository.findByTopic("life"));
        return "policy/policyListing";
    }

    @RequestMapping({"/{id}"})
    public String policyDetails(@PathVariable("id") int id, Model model){
        model.addAttribute("policy",policyService.findById(id));
        return "policy/policyDetails";
    }

    @RequestMapping({"/{id}/buy"})
    public String buyPolicy(@PathVariable("id") int id,Model model){
        if(securityService.findLoggedInUsername()==null){
            return "redirect:/login";
        }
        Policy policy = policyService.findById(id);
        // make exclusive search for vehicle and implement check functionality
        return "policy/buyPolicy";
    }
}
