package anubahv.insuracne.insuranceagency.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomepageController {

    @RequestMapping({"/","","/homepage"})
    public String homepage(Model model){
        return "homepage";
    }
}
