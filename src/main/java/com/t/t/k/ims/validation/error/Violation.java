package com.t.t.k.ims.validation.error;

/**
 * This class store the violation which is generated by model validation
 * The content on this class will return to client to display meaning full error message
 *
 * @author ttkien
 */

public class Violation {
    private final String field;

    private final String message;

    private final String rejectedValue;

    private final String model;

    public Violation(String field, String message, String rejectedValue, String model) {
        this.field = field;
        this.message = message;
        this.rejectedValue = rejectedValue;
        this.model = model;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

    public String getRejectedValue() {
        return rejectedValue;
    }

    public String getModel() {
        return model;
    }
}