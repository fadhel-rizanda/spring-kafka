package com.kafka.producer_1.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(404).body(
                new ErrorResponse(
                        "NOT_FOUND",
                        ex.getMessage(),
                        java.time.LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        return ResponseEntity.status(500).body(
                new ErrorResponse(
                        "INTERNAL_ERROR",
                        ex.getMessage(),
                        java.time.LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return ResponseEntity.status(400).body(
                new ErrorResponse(
                        "DATA_INTEGRITY_VIOLATION",
                        ex.getMessage(),
                        java.time.LocalDateTime.now()
                )
        );
    }
}

