package com.example.mvcpractice.mvc.controller;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping {
    // key : users/, value : userController
    private Map<String, Controller> mappings = new HashMap<>();

    public void init(){
        mappings.put("/", new HomeController());
        mappings.put("/users", new UserListController());
    }

    public Controller findHandler(String uriPath){
        return mappings.get(uriPath);
    }
}
