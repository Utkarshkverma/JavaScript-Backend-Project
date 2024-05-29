package com.vermau2k01.smartcontactmanager20.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
public class PageController {
    @RequestMapping("/home")
    public String Home()
    {
        System.out.println("Home");
        return "Home";
    }
}
