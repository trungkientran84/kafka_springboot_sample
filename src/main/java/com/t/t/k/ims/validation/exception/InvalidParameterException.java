package com.t.t.k.ims.validation.exception;

import com.t.t.k.ims.validation.error.Violation;

import java.util.ArrayList;
import java.util.List;

/**
 * This exception is used for hold invalid parameter error
 *
 * @author ttkien
 */
public class InvalidParameterException extends Exception {

    private List<Violation> violations = new ArrayList<>();

    /**
     * Sets the message in super class
     * @param msg message
     */
    public InvalidParameterException(String msg) {
        super(msg);
    }

    /**
     * Returns the violations variable value
     * @return violations variable value
     */
    public List<Violation> getViolations() {
        return violations;
    }

    /**
     * Sets the violations to the instance variable
     * @param violations
     */
    public void setViolations(List<Violation> violations) {
        this.violations = violations;
    }
}
