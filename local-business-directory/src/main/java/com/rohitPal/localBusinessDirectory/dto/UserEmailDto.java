package com.rohitPal.localBusinessDirectory.dto;

import com.rohitPal.localBusinessDirectory.validation.ValidMxEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserEmailDto {
    @Email(message = "invalid syntax of email , please fix it first")
    @NotBlank
    @ValidMxEmail
    private String newEmail;
    @NotBlank
    @Size(min = 6 , max = 20)
    private String password;

    public UserEmailDto() {
    }

    public UserEmailDto(String newEmail, String password) {
        this.newEmail = newEmail;
        this.password = password;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
