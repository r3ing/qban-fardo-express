package com.reing.controller;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;

@Named
@ViewScoped
public class UserBean implements Serializable {

    private String currentPassword="";
    private String newPassword="";
    private String confirmPassword="";

    public String getUsername() {
        Object userData = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (userData instanceof UserDetails) {
            return ((UserDetails) userData).getUsername();
        }

        return userData.toString();
    }

    public void changePassword() {
        if (!newPassword.equals(confirmPassword)) {
            System.out.println("Error: Passwords do not match!");
            return;
        }
        System.out.println("Password changed successfully!");
    }

    private boolean showPasswordDialog = false;

    public boolean isShowPasswordDialog() {
        return showPasswordDialog;
    }

    public void showChangePasswordDialog() {
        this.showPasswordDialog = true;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
