package com.rohitPal.localBusinessDirectory.repository;

import com.rohitPal.localBusinessDirectory.model.Business;
import com.rohitPal.localBusinessDirectory.repository.projection.BusinessProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusinessRepository extends JpaRepository<Business, Long> {

    @Query(value = """
            select b.*  ,
             ROUND( CAST((ST_Distance( b.location ,CAST(ST_SetSRID(ST_MakePoint(:longitude , :latitude) , 4326) AS geography)) * 0.001)
             as numeric),2)
             as distance_km
             from businesses b
             where
             ST_DWithin(b.location , CAST(ST_SetSRID(ST_MakePoint(:longitude , :latitude) , 4326) AS geography ), :radius)
             order by
             distance_km
            
            """ , nativeQuery = true)
    List<BusinessProjection> findNearByBusiness(@Param("longitude") double longitude,
                                                @Param("latitude") double latitude ,
                                                @Param("radius")double radius);

    boolean existsByEmail(String email);
}