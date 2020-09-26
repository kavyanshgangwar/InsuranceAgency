package anubahv.insuracne.insuranceagency.controllers;

import anubahv.insuracne.insuranceagency.models.User;
import anubahv.insuracne.insuranceagency.services.SecurityService;
import anubahv.insuracne.insuranceagency.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class RegistrationController {

    private UserService userService;
    private SecurityService securityService;

    @Autowired
    public RegistrationController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user",new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user){
        userService.save(user);
        securityService.autoLogin(user.getEmail(),user.getPassword());
        return "redirect:/welcome";
    }

    @RequestMapping("/welcome")
    public String welcome(Principal principal, Model model){
        model.addAttribute("username",securityService.findLoggedInUsername());
        return "welcome";
    }
}
