package anubahv.insuracne.insuranceagency.controllers;


import anubahv.insuracne.insuranceagency.models.User;
import anubahv.insuracne.insuranceagency.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    SecurityService securityService;

    @Autowired
    public LoginController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("user", new User());

        model.addAttribute("error_message",false);
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user){
        securityService.autoLogin(user.getEmail(), user.getPassword());
        return "redirect:/welcome";
    }


}
