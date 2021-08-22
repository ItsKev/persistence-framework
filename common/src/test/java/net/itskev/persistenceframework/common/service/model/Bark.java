package net.itskev.persistenceframework.common.service.model;

import lombok.Data;

@Data
public class Bark implements Behaviour {

  private final double volume;
}
