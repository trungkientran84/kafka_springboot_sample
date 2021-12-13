package com.t.t.k.ims.common.utils;

/**
 * This class is to create and/or provide a single instance of com.fasterxml.jackson.databind.ObjectMapper
 *
 * @author ttkien
 */
public class ObjectMapper {

    com.fasterxml.jackson.databind.ObjectMapper mapper;
    private static ObjectMapper instance;

    public static com.fasterxml.jackson.databind.ObjectMapper instance() {
        if (instance == null) {
            instance = new ObjectMapper();
        }
        return instance.getMapper();
    }


    private ObjectMapper() {
        mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        mapper.findAndRegisterModules();
    }

    public com.fasterxml.jackson.databind.ObjectMapper getMapper() {
        return mapper;
    }
}
