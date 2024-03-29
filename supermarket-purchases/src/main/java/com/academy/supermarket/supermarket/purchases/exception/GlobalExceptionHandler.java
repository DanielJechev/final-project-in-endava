package com.academy.supermarket.supermarket.purchases.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(SuperMarketAlreadyExistsException.class)
    public ResponseEntity<Object> superMarketAlreadyExists(SuperMarketAlreadyExistsException superMarketAlreadyExistsException) {
        return new ResponseEntity<>(superMarketAlreadyExistsException.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(SuperMarketNotFoundException.class)
    public ResponseEntity<Object> superMarketNotFound(SuperMarketNotFoundException superMarketNotFoundException) {
        return new ResponseEntity<>(superMarketNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<Object> itemtNotFound(ItemNotFoundException itemNotFoundException) {
        return new ResponseEntity<>(itemNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ItemTypeInvalidException.class)
    public ResponseEntity<Object> itemTypeNotFound(ItemTypeInvalidException itemTypeInvalidException) {
        return new ResponseEntity<>(itemTypeInvalidException.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(PurchaseCashAmountNotEnoughException.class)
    public ResponseEntity<Object> purchaseCashAmountNotFound(PurchaseCashAmountNotEnoughException purchaseCashAmountNotEnoughException) {
        return new ResponseEntity<>(purchaseCashAmountNotEnoughException.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        Map<String, String> fieldErrors = ex.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        body.put("errors", fieldErrors);
        return new ResponseEntity<>(body, headers, status);
    }
}
