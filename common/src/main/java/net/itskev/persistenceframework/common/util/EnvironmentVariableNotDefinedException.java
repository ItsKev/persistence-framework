package net.itskev.persistenceframework.common.util;

class EnvironmentVariableNotDefinedException extends RuntimeException {

  public EnvironmentVariableNotDefinedException(String message) {
    super(message);
  }
}
