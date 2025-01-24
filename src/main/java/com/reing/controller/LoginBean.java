package com.reing.controller;

import jakarta.faces.view.ViewScoped;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component("loginMB")
@ViewScoped
public class LoginBean  implements Serializable {

    public String getUsername() {
        Object userData = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (userData instanceof UserDetails) {
            return ((UserDetails) userData).getUsername();
        }

        return userData.toString();
    }

}
