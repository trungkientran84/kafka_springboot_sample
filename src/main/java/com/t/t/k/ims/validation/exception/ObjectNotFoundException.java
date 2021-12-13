package com.t.t.k.ims.validation.exception;

/**
 * This class is for storing "not found" exceptions
 *
 * @author ttkien
 */
public class ObjectNotFoundException extends Exception {
    private String modelName;
    private Object Id;

    /**
     * initializes the basic exception with message, this class's instance variable
     * @param modelName
     * @param id
     */
    public ObjectNotFoundException(String modelName, Object id) {
        super("Object not found in the system. " + modelName + ":" + id.toString());
        this.modelName = modelName;
        Id = id;
    }

    /**
     * Initializes the exception with given message.
     * @param message Provided message for exception.
     */
    public ObjectNotFoundException(String message) {
        super(message);
    }
}
