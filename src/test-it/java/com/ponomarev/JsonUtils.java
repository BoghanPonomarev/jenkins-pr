package com.ponomarev;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

  private static ObjectMapper objectMapper = new ObjectMapper();

  private JsonUtils() {
    throw new UnsupportedOperationException();
  }

  /**
   * Parse any entity to json.
   *
   * @param entity - entity to parse.
   * @param <T> - entity type.
   * @return - json string.
   */
  public static <T> String parseToJson(T entity) {
    try {
      return objectMapper.writeValueAsString(entity);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return null;
  }
}
