package com.rohitPal.localBusinessDirectory.config;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class AppConfig {

    // creating bean for GeometryFactory class , will use it whenever it is needed
    @Bean
    public GeometryFactory geometryFactory(){
        return new GeometryFactory(new PrecisionModel() , 4326);
    }

    //creating beans for rest client(RestClient) with timeout
    @Bean
    public RestClient restClient(){
        //configuring timeout first
        SimpleClientHttpRequestFactory factory  = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(3000);
        factory.setReadTimeout(5000);

        return RestClient.builder().baseUrl("https://nominatim.openstreetmap.org")
                .requestFactory(factory).build();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
