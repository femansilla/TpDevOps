package com.ordersystem.orderservice.exceptionHandler;

// Spring annotation to indicate that this class handles exceptions in RESTful controllers
@RestControllerAdvice
public class RestExceptionHandler {

    // Exception handler method for handling MethodArgumentNotValidException
    // Thrown when the required critera for name and email in user DTO 
    // is not presented in the request recieved from frontend/postman API
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {

        // Map to store field errors and their corresponding error messages
        Map<String, String> errors = new HashMap<>();

        // Iterate through all validation errors
        exception.getBindingResult().getAllErrors().forEach(error -> {
            // Extract the field name and error message
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            // Add the field error to the map
            errors.put(fieldName, errorMessage);
        });

        // Return a response with status code 400 (Bad Request) and the error details
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}