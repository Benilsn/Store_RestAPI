package com.gft.store.exceptions.errors;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import com.gft.store.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

        @ExceptionHandler(ConstraintViolationException.class)
        public final ResponseEntity<?> handleConstraintViolation(ConstraintViolationException ex,
                        HttpServletRequest request) {

                List<String> errors = ex.getConstraintViolations()
                                .stream()
                                .map(e -> e.getMessage())
                                .collect(Collectors.toList());

                StandardError error = new StandardError(LocalDateTime.now(),
                                HttpStatus.BAD_REQUEST.value(),
                                errors);

                return new ResponseEntity<>(error.getError(), HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(UserNotFoundException.class)
        public ResponseEntity<StandardError> UserNotFound(UserNotFoundException ex, HttpServletRequest request) {

                StandardError error = new StandardError(LocalDateTime.now(),
                                HttpStatus.NOT_FOUND.value(),
                                List.of(ex.getMessage()));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        @ExceptionHandler(ClientNotFoundException.class)
        public ResponseEntity<StandardError> ClientNotFound(ClientNotFoundException ex, HttpServletRequest request) {

                StandardError error = new StandardError(LocalDateTime.now(),
                                HttpStatus.NOT_FOUND.value(),
                                List.of(ex.getMessage()));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        @ExceptionHandler(BranchNotFoundException.class)
        public ResponseEntity<StandardError> BranchNotFoundException(BranchNotFoundException ex,
                        HttpServletRequest request) {

                StandardError error = new StandardError(LocalDateTime.now(),
                                HttpStatus.NOT_FOUND.value(),
                                List.of(ex.getMessage()));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        @ExceptionHandler(ProviderNotFoundException.class)
        public ResponseEntity<StandardError> ProviderNotFoundException(ProviderNotFoundException ex,
                        HttpServletRequest request) {

                StandardError error = new StandardError(LocalDateTime.now(),
                                HttpStatus.NOT_FOUND.value(),
                                List.of(ex.getMessage()));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        @ExceptionHandler(ProductNotFoundException.class)
        public ResponseEntity<StandardError> ProductNotFoundException(ProductNotFoundException ex,
                        HttpServletRequest request) {

                StandardError error = new StandardError(LocalDateTime.now(),
                                HttpStatus.NOT_FOUND.value(),
                                List.of(ex.getMessage()));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

}
