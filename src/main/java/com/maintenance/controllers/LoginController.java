package com.maintenance.controllers;

import com.maintenance.model.ServiceResponse;
import com.maintenance.model.LoginRequest;
import com.maintenance.services.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author bajpai
 */
@CrossOrigin
@Controller
public class LoginController {
    
    @Autowired
    private ILoginService loginService;
    
    @RequestMapping(value = "/login", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<ServiceResponse> login(@RequestBody LoginRequest loginRequest) {
        boolean result = loginService.login(loginRequest.getUsername(), loginRequest.getPassword());
        String message;
        if(result) {
            message = "Login success";
        } else {
            message = "Login Failure";
        }
        return new ResponseEntity<ServiceResponse>(new ServiceResponse(result, message), HttpStatus.OK);
    }
}
