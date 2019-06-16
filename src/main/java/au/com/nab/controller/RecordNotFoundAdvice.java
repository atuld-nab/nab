package au.com.nab.controller;

import au.com.nab.Exception.OperatorServiceException;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice

public class RecordNotFoundAdvice {
    Logger log = getLogger(RecordNotFoundAdvice.class);
    @ResponseBody
    @ExceptionHandler(OperatorServiceException.class)
    public ResponseEntity<String> serviceExceptionHandler(OperatorServiceException ex){
        return  error(NOT_FOUND,ex);
    }

    private ResponseEntity<String> error(HttpStatus status, Exception e) {
        log.error("Exception : ", e);
        return ResponseEntity.status(status).body(e.getMessage());
    }
}
