package com.t.t.k.ims.validation.error;

import com.t.t.k.ims.validation.exception.ApiException;
import com.t.t.k.ims.validation.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class is a global ControllerAdvice that handles all ConstraintViolationExceptions that bubble up to the controller level.
 * In order to catch validation errors for request bodies as well, this will also handle MethodArgumentNotValidExceptions.
 * <p>
 * What we’re doing here is simply reading information about the violations out of the exceptions and translating them
 * into our ValidationErrorResponse data structure.
 * <p>
 * Note the @ControllerAdvice annotation which makes the exception handler methods available globally to all controllers
 * within the application context.
 *
 * @author ttkien
 */


@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ErrorHandlingControllerAdvice {
    @Autowired
    private Environment env;

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponse onConstraintValidationException(ConstraintViolationException e, HttpServletRequest request) {
        // Provide debugMessage & trace if environment is not production
        List<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
        boolean isAddTrace = activeProfiles.contains("prod") ? false : true;

        ValidationErrorResponse error = new ValidationErrorResponse(
                HttpStatus.BAD_REQUEST,
                request.getRequestURI(),
                createMessageFromViolations(e.getConstraintViolations()),
                e,
                isAddTrace);

        for (ConstraintViolation violation : e.getConstraintViolations()) {
            error.getViolations().add(
                    new Violation(violation.getPropertyPath().toString(),
                            violation.getMessage(),
                            violation.getInvalidValue() == null ? "null" : violation.getInvalidValue().toString(),
                            violation.getRootBeanClass().toString()));
        }
        return error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponse onMethodArgumentNotValidException(
            MethodArgumentNotValidException e, HttpServletRequest request) {

        // Provide debugMessage & trace if environment is not production
        List<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
        boolean isAddTrace = activeProfiles.contains("prod") ? false : true;

        ValidationErrorResponse error = new ValidationErrorResponse(
                HttpStatus.BAD_REQUEST,
                request.getRequestURI(),
                createMessageFromFieldErrors(e.getBindingResult().getAllErrors()),
                e,
                isAddTrace);

        for (ObjectError err : e.getBindingResult().getAllErrors()) {
            String objectName = err.getObjectName();
            String rejectedValue = "null";
            if(err instanceof FieldError){
                FieldError fieldError = (FieldError) err;
                objectName = fieldError.getField();
                rejectedValue = fieldError.getRejectedValue() != null ? fieldError.getRejectedValue().toString() : "null";
            }
            error.getViolations().add(
                    new Violation(objectName,
                            err.getDefaultMessage(),
                            rejectedValue,
                            err.getClass().toString()
                    ));
        }

        return error;
    }

    /**
     * Handle response for ObjectNotFoundException
     *
     * @param e       ObjectNotFoundException
     * @param request
     * @return
     */
    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseBody
    ResponseEntity<ApiError> onObjectNotFoundException(ObjectNotFoundException e, HttpServletRequest request) {
        // Provide debugMessage & trace if environment is not production
        List<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
        boolean isAddTrace = activeProfiles.contains("prod") ? false : true;

        ApiError error = new ApiError(HttpStatus.NOT_FOUND, request.getRequestURI(), e.getMessage(), e, isAddTrace);

        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle response for common API Exception
     *
     * @param e       ApiException
     * @param request
     * @return
     */
    @ExceptionHandler(ApiException.class)
    @ResponseBody
    ResponseEntity<ApiError> onApiException(ApiException e, HttpServletRequest request) {
        // Provide debugMessage & trace if environment is not production
        List<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
        boolean isAddTrace = activeProfiles.contains("prod") ? false : true;

        ApiError error = new ApiError(HttpStatus.BAD_REQUEST, request.getRequestURI(), e.getMessage(), e, isAddTrace);
        error.getInfo().putAll(e.getInfo());

        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Create a Error Message string from list of errors.
     * @param violations Set of ConstraintViolations.
     * @return Message string for first violation in the given Set.
     */
    private String createMessageFromViolations(Set<ConstraintViolation<?>> violations) {
        List<String> errorMessage = violations.stream().map(vio -> vio.getMessage()).collect(Collectors.toList());
        return String.join(" | ", errorMessage);
    }

    /**
     * Create a Error Message string from list of errors.
     * @param errors List of FieldErrors.
     * @return Message string for first error in the given List.
     */
    private String createMessageFromFieldErrors(List<ObjectError> errors) {
        List<String> errorMessages = errors.stream().map(e -> {
            if (e instanceof FieldError) {
                // Check if the message belongs to javax.validation package, then add field name in the beginning.
                // Example: "must not be null" -> "id must not be null".
                ConstraintViolation vio = e.unwrap(ConstraintViolation.class);
                if (vio.getMessageTemplate().contains("javax.validation.constraints")) {
                    return ((FieldError) e).getField() + " " + e.getDefaultMessage();
                }
            }
            // else the message doesn't need to be modified.
            return e.getDefaultMessage();
        }).collect(Collectors.toList());
        return String.join(" | ", errorMessages);
    }
}
