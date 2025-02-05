package com.reing.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
//@SessionScoped
//@ViewScoped
@RequestScoped
public class ChangePasswordBean implements Serializable {

    private String currentPassword;
    private String newPassword;
    private String confirmPassword;

    public void changePassword() {
        if (!newPassword.equals(confirmPassword)) {
            System.out.println("Error: Passwords do not match!");
            return;
        }
        System.out.println("Password changed successfully!");
        // Aquí puedes agregar lógica para actualizar la contraseña en la base de datos
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
