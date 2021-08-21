package net.itskev.persistenceframework.proxy;

import net.md_5.bungee.api.plugin.Plugin;

public class PersistenceFrameworkPlugin extends Plugin {

  @Override
  public void onEnable() {
    System.out.println("env: " + System.getenv("PROXY_SERVICE_HOST"));
  }
}
