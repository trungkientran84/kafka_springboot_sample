package com.t.t.k.ims.validation.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * This class wrap common information of Api error. The detail error will extend this class to provide more information
 *
 * @author ttkien
 */
public class ApiError {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private int status;

    private String error;

    private String message;

    private String debugMessage;

    private String requestUri;

    private Map<String, String> info = new HashMap<>();

    //TODO: Remove this on production
    private String trace = "";

    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    ApiError(HttpStatus status) {
        this();
        this.status = status.value();
        this.error = status.getReasonPhrase();
    }

    ApiError(HttpStatus status, Throwable ex, Boolean isAddTrace) {
        this(status);
        this.message = ex.getMessage();

        if(isAddTrace){
            this.debugMessage = ex.getLocalizedMessage();
            for (StackTraceElement element : ex.getStackTrace()) {
                this.trace += element.toString() + " | ";
            }
        }
    }

    ApiError(HttpStatus status, String requestUri, String message, Throwable ex, Boolean isAddTrace) {
        this(status, ex, isAddTrace);
        this.requestUri = requestUri;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public Map<String, String> getInfo() {
        return info;
    }

    public String getTrace() {
        return trace;
    }
}
