package com.t.t.k.ims.common.utils;

import org.modelmapper.convention.MatchingStrategies;

/**
 * This class is to create and/or provide a single instance of ModelMapper
 *
 * @author ttkien
 */
public class ModelMapper {

    org.modelmapper.ModelMapper modelMapper;
    private static ModelMapper instance;

    public static org.modelmapper.ModelMapper intance(){
        if(instance== null){
            instance = new ModelMapper();
        }
        return instance.getModelMapper();
    }


    private ModelMapper(){
        modelMapper = new org.modelmapper.ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public org.modelmapper.ModelMapper getModelMapper(){
        return modelMapper;
    }
}
