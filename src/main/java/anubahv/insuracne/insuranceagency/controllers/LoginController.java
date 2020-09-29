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

@Controller
public class LoginController {

    SecurityService securityService;
    UserService userService;
    @Autowired
    public LoginController(SecurityService securityService,UserService userService) {
        this.securityService = securityService;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("user", new User());

        model.addAttribute("error_message",false);
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user,Model model){
        User userFromDatabase = userService.findByUsername(user.getEmail());
        if(userFromDatabase.getStatus().equals("not-verified")){
            model.addAttribute("verification",false);
            return "/login?error=true";
        }
        securityService.autoLogin(user.getEmail(), user.getPassword());
        return "redirect:/welcome";
    }


}
