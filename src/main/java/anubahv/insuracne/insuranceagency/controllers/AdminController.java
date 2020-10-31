package anubahv.insuracne.insuranceagency.controllers;

import anubahv.insuracne.insuranceagency.models.User;
import anubahv.insuracne.insuranceagency.services.SecurityService;
import anubahv.insuracne.insuranceagency.services.UserDetailsServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class AdminController {
    @RequestMapping({"","/"})
    public String adminHome(){
        return "admin/adminHome";
    }

}
