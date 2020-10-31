package anubahv.insuracne.insuranceagency.controllers;

import anubahv.insuracne.insuranceagency.models.Policy;
import anubahv.insuracne.insuranceagency.models.User;
import anubahv.insuracne.insuranceagency.services.PolicyService;
import anubahv.insuracne.insuranceagency.services.SecurityService;
import anubahv.insuracne.insuranceagency.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin")
@Controller
public class AdminController {
    PolicyService policyService;
    SecurityService securityService;

    @Autowired
    public AdminController(PolicyService policyService, SecurityService securityService) {
        this.policyService = policyService;
        this.securityService = securityService;
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
}
