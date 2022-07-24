package com.gurshobit.employeemanagementsystem.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;

@RestController
public class HomeController implements ErrorController {

    @RequestMapping("")
    public String index(){
        return "redirect:/api/employee/list";
    }

    @RequestMapping(value = "/404", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public HashMap<String, Object> pageNotFound(Principal user) {
        HashMap<String, Object> responseMap = new HashMap<>();

        String message;

        if (user != null) {
            message = "Hi " + user.getName() + ", Page your looking for doesn't exists!";
        } else {
            message = "Page your looking for doesn't exists!";
        }

        HashMap<String,String> info = new HashMap<>();
        info.put("message",message);

        responseMap.put("success", false);
        responseMap.put("info", info);

        return responseMap;

    }

    @RequestMapping(value = "/403", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public HashMap<String, Object> accessDenied(Principal user) {
        HashMap<String, Object> responseMap = new HashMap<>();

        String message;

        if (user != null) {
            message = "Hi " + user.getName()
                    + ", you do not have permission to access this page!";
        } else {
            message = "You do not have permission to access this page!";
        }

        HashMap<String,String> info = new HashMap<>();
        info.put("message",message);

        responseMap.put("success", false);
        responseMap.put("info", info);

        return responseMap;
    }

    @RequestMapping(value = "/errors", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public HashMap<String, Object> whiteLabelErrors(HttpServletRequest httpServletRequest, Principal user) {
        HashMap<String, Object> responseMap = new HashMap<>();

        String message;

        Object status = httpServletRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                if (user != null) {
                    message = "Hi " + user.getName() + ", Page your looking for doesn't exists!";
                } else {
                    message = "Page your looking for doesn't exists!";
                }
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                if (user != null) {
                    message = "Oh Snap " + user.getName() + ", There is some error in code!";
                } else {
                    message = "Oh Snap, There is some error in code!";
                }
            } else if(statusCode == HttpStatus.UNAUTHORIZED.value()){
                if (user != null) {
                    message = "Hi " + user.getName()
                            + ", you need to login first to access this page!";
                } else {
                    message = "You need to login first to access this page!";
                }
            } else {
                if (user != null) {
                    message = "Hi " + user.getName()
                            + ", Some Error is there!";
                } else {
                    message = "Some Error is there!";
                }
            }

        } else {
            message = "Not valid Status Code!";
        }

        HashMap<String,String> info = new HashMap<>();
        info.put("message",message);

        responseMap.put("success", false);
        responseMap.put("info", info);

        return responseMap;
    }
}
