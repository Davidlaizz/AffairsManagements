package com.pigeon.affairsmanagements.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class MainController {

    @RequestMapping("/index")
    @ResponseBody
    public String index()
    {
        return "This is your first SpringBoot Application!!";
    }

    @RequestMapping("/")
    public ModelAndView login()
    {
/*        return "forward:login.html";*/
        return new ModelAndView("login.html");
    }

}
