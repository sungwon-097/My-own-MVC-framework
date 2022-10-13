package com.example.mvcpractice.Controller;

import com.example.mvcpractice.annotation.Controller;
import com.example.mvcpractice.annotation.RequestMapping;
import com.example.mvcpractice.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(HttpServletRequest request, HttpServletResponse response){
        return "home";
    }
}
