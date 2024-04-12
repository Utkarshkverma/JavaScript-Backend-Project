package com.thinkconstructive.demogradel;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/employee")
public class Empolyee {
    
    @GetMapping("/{empId}")
    public String getEmployee(String empId)
    {
       return "Employee Found";
    }
}
