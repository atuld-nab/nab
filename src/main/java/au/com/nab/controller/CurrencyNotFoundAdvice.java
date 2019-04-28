package au.com.nab.controller;

import au.com.nab.Exception.CurrencyServiceException;
import au.com.nab.LoadDataBase;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice

public class CurrencyNotFoundAdvice {
    Logger log = getLogger(CurrencyNotFoundAdvice.class);
    @ResponseBody
    @ExceptionHandler(CurrencyServiceException.class)
    public ResponseEntity<String> serviceExceptionHandler(CurrencyServiceException ex){
        return  error(NOT_FOUND,ex);
    }

    private ResponseEntity<String> error(HttpStatus status, Exception e) {
        log.error("Exception : ", e);
        return ResponseEntity.status(status).body(e.getMessage());
    }
}
