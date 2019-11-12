package com.maintenance.controllers;

import com.maintenance.model.RequestStatus;
import com.maintenance.model.Tower;
import com.maintenance.services.IStatusService;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author bajpai
 */
@CrossOrigin
@Controller
public class StatusController {
    
    @Autowired
    private IStatusService statusService;
    
    @RequestMapping(value = "/status", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<List<RequestStatus>> getAllStatuses() {
        return new ResponseEntity<List<RequestStatus>>(statusService.getAllStatuses(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/towers", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<List<Tower>> getAllTowers() {
        return new ResponseEntity<List<Tower>>(Arrays.asList(Tower.values()), HttpStatus.OK);
    }
    
}
