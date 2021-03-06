package net.itskev.persistenceframework.proxy;

import com.mongodb.ConnectionString;
import com.mongodb.MongoCredential;
import lombok.Getter;
import net.itskev.persistenceframework.common.api.MongoDbService;
import net.itskev.persistenceframework.common.service.DefaultMongoDbService;
import net.itskev.persistenceframework.common.util.EnvironmentVariableHelper;
import net.md_5.bungee.api.plugin.Plugin;

@Getter
public class PersistenceFrameworkPlugin extends Plugin {

  private MongoDbService mongoDbService;

  @Override
  public void onEnable() {
    String connectionString = EnvironmentVariableHelper.getEnvironmentVariable("CONNECTION_STRING");
    String authenticationDatabase = EnvironmentVariableHelper.getEnvironmentVariable("AUTHENTICATION_DATABASE");
    String username = EnvironmentVariableHelper.getEnvironmentVariable("USERNAME");
    String password = EnvironmentVariableHelper.getEnvironmentVariable("PASSWORD");

    mongoDbService = new DefaultMongoDbService(
        new ConnectionString(connectionString),
        MongoCredential.createCredential(username, authenticationDatabase, password.toCharArray())
    );
  }
}
