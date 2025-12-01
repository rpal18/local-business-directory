package com.rohitPal.localBusinessDirectory.controller;
import com.rohitPal.localBusinessDirectory.dto.BusinessDto;
import com.rohitPal.localBusinessDirectory.dto.BusinessResponse;
import com.rohitPal.localBusinessDirectory.dto.UserResponse;
import com.rohitPal.localBusinessDirectory.service.BusinessService;
import com.rohitPal.localBusinessDirectory.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/admin/businesses")
@Validated
public class BusinessController {

    private BusinessService businessService;

    private final UserService userService;

    @Autowired
    public BusinessController(BusinessService businessService,
                              UserService userService
    ) {
        this.businessService = businessService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<BusinessResponse> registerBusiness(@Valid @RequestBody BusinessDto businessDto) {
        BusinessResponse response = businessService.addBusiness(businessDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BusinessResponse> deleteBusiness(@PathVariable long id) {
        BusinessResponse response = businessService.deleteBusiness(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{id}/phone")
    public ResponseEntity<BusinessResponse> updatePhoneNumber(@PathVariable Long id,
                                                              @NotBlank(message = "phone number can not be null")
                                                              @Pattern(regexp = "^(\\+91[\\-\\s]?)?[6-9]\\d{9}$",
                                                                      message = "mobile number invalid!! Enter Valid" +
                                                                              " Indian  mobile number :")
                                                              @RequestParam String phoneNumber) {
        BusinessResponse response = businessService.updateBusinessPhoneNumber(id, phoneNumber);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{id}/location")
    public ResponseEntity<BusinessResponse> updateLocation(@PathVariable Long id,
                                                           @NotBlank(message = "Location can not be null")
                                                           @Size(min = 5, max = 100,
                                                                   message = "enter complete address")
                                                           @RequestBody String address) {
        BusinessResponse response = businessService.updateBusinessLocation(id, address);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<BusinessResponse>> getAllBusiness(@PageableDefault(size = 10)
                                                                 Pageable pageable) {
        Page<BusinessResponse> response = businessService.getAllBusiness(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/nearby")
    public ResponseEntity<List<BusinessResponse>> getNearestBusiness(
            @RequestParam(required = false) Double latitude,
            @RequestParam(required = false) Double longitude,
            @RequestParam(required = false) Long userId,
            @RequestParam(defaultValue = "500000") double radius
    ) {
        double searchLat;
        double searchLon;

        if (latitude != null && longitude != null) {
            searchLat = latitude;
            searchLon = longitude;
        }
        else if (userId != null) {
            UserResponse user = userService.getUserById(userId);

            if (user.getLatitude() == 0.0 && user.getLongitude() == 0.0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User has no saved home location. Please provide coordinates.");
            }

            searchLat = user.getLatitude();
            searchLon = user.getLongitude();
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please provide either (lat & lon) OR userId");
        }
        List<BusinessResponse> response = businessService.getNearbyBusinessWithin(searchLon, searchLat, radius);

        return ResponseEntity.ok(response);
    }
    @GetMapping("/KNN")
    public ResponseEntity<List<BusinessResponse>> getKNearestBusiness( @RequestParam(required = false) Double latitude,
                                                                       @RequestParam(required = false) Double longitude,
                                                                       @RequestParam(required = false) Long userId,
                                                                       @RequestParam(required = false , defaultValue = "10") int count){
        // first priority if user is allowing his current location
        double searchLon;
        double searchLat;

        if(longitude!=null && latitude!=null){
            searchLat = latitude;
            searchLon = longitude;
        }else if(userId!=null){
            UserResponse response = userService.getUserById(userId);
            if(response.getLatitude()==0.0 && response.getLongitude() == 0.0){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User has no saved home location. Please provide coordinates.");
            }
            searchLat = response.getLatitude();
            searchLon = response.getLongitude();
        }  else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please provide either (lat & lon) OR userId");
        }

        List<BusinessResponse> response = businessService.getKNearestBusiness(searchLon , searchLat , count);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}



