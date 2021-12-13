package com.t.t.k.ims.validation.error;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * This class wrap a list of bean validation error message which will return to client after been validation
 *
 * @author ttkien
 */

public class ValidationErrorResponse extends ApiError {

    private List<Violation> violations = new ArrayList<>();


    public ValidationErrorResponse(HttpStatus status, String requestUri, String message, Throwable ex, Boolean isAddTrace) {
        super(status, requestUri, message, ex, isAddTrace);
    }

    public List<Violation> getViolations() {
        return violations;
    }

    public void setViolations(List<Violation> violations) {
        this.violations = violations;
    }
}
