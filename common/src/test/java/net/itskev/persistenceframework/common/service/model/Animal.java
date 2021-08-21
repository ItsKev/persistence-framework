package net.itskev.persistenceframework.common.service.model;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
public class Animal {

  private final String name;
  private final int age;
}
