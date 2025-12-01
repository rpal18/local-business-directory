package com.rohitPal.localBusinessDirectory.controller;

import com.rohitPal.localBusinessDirectory.dto.UserDto;
import com.rohitPal.localBusinessDirectory.dto.UserEmailDto;
import com.rohitPal.localBusinessDirectory.dto.UserResponse;
import com.rohitPal.localBusinessDirectory.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/users")
@Validated
public class UserController {

    private UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserDto userDto){
        UserResponse userResponse = userService.addUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }
    @DeleteMapping("/{id}/")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable Long id){
        UserResponse response = userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{id}/email")
    public ResponseEntity<UserResponse> updateEmail(@PathVariable Long id ,@Valid @RequestBody UserEmailDto emailDto){
        UserResponse response = userService.updateUserEmail(id , emailDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PatchMapping("/{id}/address")
    public ResponseEntity<UserResponse> updateAddress(@PathVariable Long id , @RequestParam
    /*
    here at first I was not using these validation annotations .during testing on postman when I tried to
    bind the raw input it was bypassing the validation for address that's I am adding it here.
     */
    @NotBlank(message = "Address cannot be empty")
    @Size(min = 5, max = 255, message = "Address must be between 5 and 255 chars")String address){
        UserResponse response = userService.updateAddress(id , address);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id){
        UserResponse response = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
