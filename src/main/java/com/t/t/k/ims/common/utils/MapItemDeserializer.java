package com.t.t.k.ims.common.utils;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.t.t.k.ims.model.orders.Item;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MapItemDeserializer extends JsonDeserializer<Map<String, Item>> {


    @Override
    public Map<String, Item> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();

        if (jsonParser.getCurrentToken().equals(JsonToken.START_OBJECT)) {
            return mapper.readValue(jsonParser, new TypeReference<HashMap<String, Item>>() {
            });
        } else {
            //consume this stream
            mapper.readTree(jsonParser);
            return new HashMap<String, Item>();
        }
    }
}
