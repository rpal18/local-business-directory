package com.rohitPal.localBusinessDirectory.service;

import com.rohitPal.localBusinessDirectory.dto.UserDto;
import com.rohitPal.localBusinessDirectory.dto.UserEmailDto;
import com.rohitPal.localBusinessDirectory.dto.UserResponse;
import com.rohitPal.localBusinessDirectory.exception.*;
import com.rohitPal.localBusinessDirectory.model.User;
import com.rohitPal.localBusinessDirectory.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private OpenStreetMapGeocodingService openStreetMapGeocodingService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, OpenStreetMapGeocodingService openStreetMapGeocodingService) {
        this.userRepository = userRepository;
        this.openStreetMapGeocodingService = openStreetMapGeocodingService;
    }

    @Override
    @Transactional
    public UserResponse addUser(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) throw new UserAlreadyExists("User already" +
                "registered kindly login  ");

        User user = new User();
        user.setContactNumber(userDto.getContactNumber());
        user.setAddress(userDto.getAddress());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setDateOfBirth(userDto.getDob());

        String address = userDto.getAddress();

        Point location = openStreetMapGeocodingService.getCoordinates(address).orElseThrow(() ->
                new LocationNotFoundException("please enter a valid address"));

        user.setLocation(location);
        User savedUser = userRepository.save(user);
        return userMapper(savedUser);
    }

    @Override
    @Transactional
    public UserResponse deleteUser(Long id) {
        User savedUser = userRepository.findById(id).orElseThrow(() -> new UserNotFound("User never registered"));
        userRepository.deleteById(id);
        return userMapper(savedUser);
    }

    @Override
    @Transactional
    /*
    here in the below example , we need to verify whether the email id belongs to same person or not.
    ( for security purpose ).
     */
    public UserResponse updateUserEmail(Long id, UserEmailDto userEmailDto) {
        User savedUser = userRepository.findById(id).orElseThrow(() -> new UserNotFound("User never registered"));
        //Integrity check : checking whether this email is already registered by any user or not
        if (userRepository.existsByEmail(userEmailDto.getNewEmail())) {
            throw new EmailAlreadyExistsException(
                    "This Email has already been taken , please enter  different email");
        }

        // here dirty checking will happen , so I do not need to put it here  .

            if (!savedUser.getPassword().equals(userEmailDto.getPassword())) {
                throw new InvalidPasswordException("Password mismatch , please enter correct password");
            }

        // here dirty checking will happen , so I do not need to put it here  .
        savedUser.setEmail(userEmailDto.getNewEmail());
        User updatedUser = userRepository.save(savedUser);

        return userMapper(updatedUser);

    }

    @Override
    @Transactional
    public UserResponse updateAddress(Long id, String address) {
         User savedUser = userRepository.findById(id).orElseThrow(() ->
                 new UserNotFound("please register first to update the address"));

         //should not be same as old
        if( savedUser.getAddress()!=null &&savedUser.getAddress().equalsIgnoreCase(address)){
            return userMapper(savedUser);
        }

        savedUser.setAddress(address);

        //geocoding service
        Point updatedLocation = openStreetMapGeocodingService.getCoordinates(address).orElseThrow(() ->
                new LocationNotFoundException("invalid address , Location not found"));

        savedUser.setLocation(updatedLocation);
        User updatedUser = userRepository.save(savedUser);

        return userMapper(updatedUser);


    }

    @Override
    public UserResponse getUserById(Long id) {
        User user  = userRepository.findById(id).orElseThrow(()->
                new UserNotFound("User never registered"));
        return userMapper(user);
    }

    private UserResponse userMapper(User user) {
        if(user == null) return null;
        UserResponse userResponse = new UserResponse();
        userResponse.setUserName(user.getName());
        userResponse.setAddress(user.getAddress());
        userResponse.setEmail(user.getEmail());
        userResponse.setUserId(user.getId());
        Point point = user.getLocation();
        /*
        Here , it could be possible that we have null point ( no location found)
         */
        if(point!=null) {
            userResponse.setLatitude(point.getY());
            userResponse.setLongitude(point.getX());
        }
        return userResponse ;
    }
}
