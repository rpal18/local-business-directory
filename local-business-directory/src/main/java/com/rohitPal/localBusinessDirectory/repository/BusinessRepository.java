package com.rohitPal.localBusinessDirectory.repository;

import com.rohitPal.localBusinessDirectory.model.Business;
import com.rohitPal.localBusinessDirectory.repository.projection.BusinessProjection;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusinessRepository extends JpaRepository<Business, Long> {

    /*
    --------------------------------------------------------------------------------------------------
    Mistake i made first using this native query :

     @Query(value = """
            select b.*  ,
             ROUND( CAST((ST_Distance( b.location ,CAST(ST_SetSRID(ST_MakePoint(:longitude , :latitude) , 4326) AS geography)) * 0.001)
             as numeric),2)
             as distance
             from businesses b
             where
             ST_DWithin(b.location , CAST(ST_SetSRID(ST_MakePoint(:longitude , :latitude) , 4326) AS geography ), :radius)
             order by
             distance

            """ , nativeQuery = true)
    List<BusinessProjection> findNearByBusiness(@Param("longitude") double longitude,
                                                @Param("latitude") double latitude ,
                                                @Param("radius")double radius);



     when i used this native query . I got null pointer exception because my projection interface
     was expecting me to return a business entity but I was not returning business instead I was
     returning a new table that was created by database after running this query .

     fix : I used Jpql and avoided native query because Jpql is handled by hibernate and hibernate
     is intelligent enough to convert it into Business Entity.

     to make query readable i will bind Point here , instead of longitude and latitude .

    --------------------------------------------------------------------------------------------------
     */

//    @Query("SELECT b as business, " +
//            "function('ST_Distance', b.location, :point) as distance " +
//            "FROM Business b " +
//            "WHERE function('ST_DWithin', b.location, :point, :radius) = true " +
//            "ORDER BY distance ASC")
//    List<BusinessProjection> findNearByBusiness(@Param("point") Point point,
//
//                                             @Param("radius") double radius);



    @Query(value = """
    SELECT
        b.id AS businessId,
        b.business_name AS businessName,
        b.contact_number AS contactNumber,
        b.email AS email,
        b.website AS website,
        b.address AS address,
        b.category AS category,
        
        --Cast Geography to Geometry to extract Lat/Lon
        ST_X(b.location::geometry) AS longitude,
        ST_Y(b.location::geometry) AS latitude,
        
        -- Calculate Distance (Geography)
        ST_Distance(b.location, ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326)::geography) AS distance
        
    FROM businesses b
    
    -- FIX 3: Radius Search using DWithin (Geography)
    WHERE ST_DWithin(
        b.location,
        ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326)::geography,
        :radius
    ) = true
    
    ORDER BY distance ASC
    """, nativeQuery = true)
    List<BusinessProjection> findNearByBusiness(
            @Param("longitude") double longitude,
            @Param("latitude") double latitude,
            @Param("radius") double radiusInMeters
    );


    boolean existsByEmail(String email);

    /*
    Here , I will make use of KNN(<-> operator ) to find k nearest business because
    it works on GIST(Generalised search tree ) that speeds up searching and it does not
    scan whole table but indexes.

    production tip :
    we should use two-Step approach while working with KNN .
    1) find id only using knn operator ( by making use of native query )
    2) take these id's and calculate distance using jpql ( which will internally map it into entity
     , there is no need to map it manually .
     */
/*
------------------------------------------------------------------------------------------------------------------------

    @Query(value = """
            Select b.* , ROUND(
            CAST(ST_Distance(
            b.location::geography , ST_SetSRID(ST_MakePoint(:longitude , :latitude) , 4326)::geography)
            as numeric) , 2)
            AS distance
            from business b
            WHERE b.location IS NOT NULL
            order by
            b.location <-> ST_SetSRID(ST_MakePoint(:longitude , :latitude) , 4326)
            limit :k
            """ , nativeQuery = true)
    List<BusinessProjection> findKNearestBusinesses( @Param("longitude") double longitude,
                                                      @Param("latitude") double latitude,
                                                      @Param("k") int k);

------------------------------------------------------------------------------------------------------------------------

 */



    @Query(value = """
    SELECT
        b.id AS businessId,
        b.business_name AS businessName,
        b.contact_number AS contactNumber,
        b.email AS email,
        b.website AS website,
        b.address AS address,
        b.category AS category,
        -- Cast geography to geometry to get coordinates
        ST_X(b.location::geometry) AS longitude,
        ST_Y(b.location::geometry) AS latitude,
        -- FIX 1: Use ST_Distance with explicit cast to geography
        ST_Distance(b.location, ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326)::geography) AS distance
    FROM businesses b
    -- FIX 2: Match types in the ORDER BY clause for index usage
    ORDER BY b.location <-> ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326)::geography
    LIMIT :k
    """, nativeQuery = true)
    List<BusinessProjection> findKNearestBusinesses(@Param("longitude") double longitude,
                                                   @Param("latitude") double latitude,
                                                   @Param("k") int k);

}