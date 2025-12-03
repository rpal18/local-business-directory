package com.rohitPal.localBusinessDirectory.service;

import com.rohitPal.localBusinessDirectory.dto.BusinessDto;
import com.rohitPal.localBusinessDirectory.dto.BusinessResponse;
import com.rohitPal.localBusinessDirectory.repository.projection.BusinessProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BusinessService {
    BusinessResponse addBusiness(BusinessDto businessDto);
    BusinessResponse deleteBusiness(Long id);

    BusinessResponse updateBusinessPhoneNumber(Long id ,String contactNumber);

    BusinessResponse updateBusinessLocation(Long id ,String address);

    Page<BusinessResponse> getAllBusiness(Pageable pageDetails);

    BusinessResponse getBusinessById(Long id);


    List<BusinessResponse> findKNearestBusinessesimpl(Double longitude, Double latitude, int k);

    List<BusinessResponse> getNearbyBusinessWithin(Double longitude , Double latitude , Double radius);

}
