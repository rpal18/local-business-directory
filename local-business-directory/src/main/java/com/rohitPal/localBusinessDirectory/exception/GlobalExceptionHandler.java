package com.rohitPal.localBusinessDirectory.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BusinessAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleAlreadyExistsException(BusinessAlreadyExistsException e , HttpServletRequest req){
      logger.warn("Conflict : {}" , e.getMessage());
      ApiError error = new ApiError(
          e.getMessage(),Instant.now().toString(),
              HttpStatus.CONFLICT.getReasonPhrase(),
              req.getRequestURI(),
              HttpStatus.CONFLICT.value()
      );

      return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(BusinessNotFoundException.class)
    public ResponseEntity<ApiError> handleBusinessNotFoundException(BusinessNotFoundException e , HttpServletRequest req){
        logger.warn("NOT FOUND : {}" , e.getMessage());
        ApiError error = new ApiError(e.getMessage() , Instant.now().toString() ,
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                req.getRequestURI(),
                HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
