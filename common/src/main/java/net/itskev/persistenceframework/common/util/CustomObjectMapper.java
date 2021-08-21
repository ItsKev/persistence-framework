package net.itskev.persistenceframework.common.util;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomObjectMapper {

  public static ObjectMapper create() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    objectMapper.setDefaultTyping(createCustomTypeResolver());

    return objectMapper;
  }

  private static CustomTypeResolver createCustomTypeResolver() {
    CustomTypeResolver customTypeResolver = new CustomTypeResolver();
    customTypeResolver.init(JsonTypeInfo.Id.CLASS, null);
    customTypeResolver.inclusion(JsonTypeInfo.As.PROPERTY);
    return customTypeResolver;
  }
}
