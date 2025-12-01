package com.rohitPal.localBusinessDirectory.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.rohitPal.localBusinessDirectory.validation.ValidMxEmail;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class UserDto {
    @NotBlank(message = "User name should not be blank")
    @Size(min = 2, max = 100)
    private String name;
    @Email
    @NotBlank(message = "email cannot be blank")
    @ValidMxEmail
    private String email;
    @NotBlank(message = "please enter mobile number:")
    @Pattern(regexp = "^(\\+91[\\-\\s]?)?[6-9]\\d{9}$" , message = "mobile number invalid!! Enter Valid Indian  mobile number :")
    private String contactNumber;
    @NotNull(message = "please enter date of birth")
    @Past // here I am using @Past annotation to avoid impossible time ( user who is going to register themselves
    // must be born before .( avoiding time traveller bug)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dob;
    @NotBlank
    @Size(min = 5 , max = 255 , message = "address length should be between 5 and 255 character")
    private String address;
    @NotBlank(message = "Password is required")
    @Size(min = 2 , max = 20)
    private String password;

    public UserDto() {
    }

    public UserDto(String name, String email, String contactNumber, LocalDate dob, String address , String password) {
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.dob = dob;
        this.address = address;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
