package net.itskev.persistenceframework.common.service.model;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
public class Animal {

  private final String name;
  private final int age;
  private final Behaviour behaviour;
  private final boolean ableToBreatheUnderwater;
  private final Gender gender;

  public enum Gender {
    MALE,
    FEMALE
  }
}
