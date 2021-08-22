package net.itskev.persistenceframework.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EnvironmentVariableHelper {

  public static String getEnvironmentVariable(String name) {
    String environmentVariable = System.getenv(name);
    if (environmentVariable == null) {
      throw new EnvironmentVariableNotDefinedException("The environment variable " + name + " was not set.");
    }

    return environmentVariable;
  }
}
