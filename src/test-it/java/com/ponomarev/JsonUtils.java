package com.ponomarev;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    public static ObjectMapper objectMapper = new ObjectMapper();

    private JsonUtils() {
        throw new UnsupportedOperationException();
    }

    public static <T> String mapToJson(T entity) {
        try {
           return objectMapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
