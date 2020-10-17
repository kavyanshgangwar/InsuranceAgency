package anubahv.insuracne.insuranceagency.controllers;

import anubahv.insuracne.insuranceagency.models.User;
import anubahv.insuracne.insuranceagency.services.SecurityService;
import anubahv.insuracne.insuranceagency.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserProfileController {
    UserService userService;
    SecurityService securityService;

    @Autowired
    public UserProfileController(UserService userService,SecurityService securityService) {
        this.userService = userService;
        this.securityService =securityService;
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
}
