package com.t.t.k.ims.validation.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * This exception is used for hold invalid parameter error
 *
 * @author ttkien
 */
public class ApiException extends Exception {

    private Map<String, String> info = new HashMap<>();

    /**
     * Sets the message in super class
     * @param msg message
     */
    public ApiException(String msg) {
        super(msg);
    }

    /**
     * Returns instance variable info
     * @return instance variable info
     */
    public Map<String, String> getInfo() {
        return info;
    }

    /**
     * Sets the value for instance variable info
     * @param info information
     */
    public void setInfo(Map<String, String> info) {
        this.info = info;
    }
}
