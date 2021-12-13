package com.t.t.k.ims.validation.error;

import com.t.t.k.ims.validation.exception.InvalidParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * This class is a global ControllerAdvice that handles exception that bubble up to the controller level.
 * <p>
 * Note the @ControllerAdvice annotation which makes the exception handler methods available globally to all controllers
 * within the application context.
 *
 * @author ttkien
 */


@ControllerAdvice
public class RestGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    private Environment env;

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.valueOf(statusCode);
    }

    /**
     * Handle custom error response for InvalidParameterException
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(InvalidParameterException.class)
    @ResponseBody
    ResponseEntity<?> handleInvalidParameterException(HttpServletRequest request, Throwable ex) {
        // Provide debugMessage & trace if environment is not production
        List<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
        boolean isAddTrace = activeProfiles.contains("prod") ? false : true;

        HttpStatus status = getStatus(request);
        ValidationErrorResponse response = new ValidationErrorResponse(HttpStatus.BAD_REQUEST, request.getRequestURI(), ex.getMessage(), ex, isAddTrace);

        response.getViolations().addAll(((InvalidParameterException) ex).getViolations());

        return new ResponseEntity(response, status);

    }


    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        // Provide debugMessage & trace if environment is not production
        List<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
        boolean isAddTrace = activeProfiles.contains("prod") ? false : true;

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute("javax.servlet.error.exception", ex, 0);
        }

        ApiError error = new ApiError(status, ((ServletWebRequest) request).getRequest().getRequestURI(), ex.getMessage(), ex, isAddTrace);

        return new ResponseEntity(error, headers, status);
    }

}
