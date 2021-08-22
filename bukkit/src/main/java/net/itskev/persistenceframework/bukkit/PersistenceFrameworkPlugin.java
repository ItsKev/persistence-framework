package net.itskev.persistenceframework.bukkit;

import com.mongodb.ConnectionString;
import com.mongodb.MongoCredential;
import net.itskev.persistenceframework.common.api.MongoDbService;
import net.itskev.persistenceframework.common.service.DefaultMongoDbService;
import net.itskev.persistenceframework.common.util.EnvironmentVariableHelper;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public class PersistenceFrameworkPlugin extends JavaPlugin {

  @Override
  public void onEnable() {
    String connectionString = EnvironmentVariableHelper.getEnvironmentVariable("CONNECTION_STRING");
    String authenticationDatabase = EnvironmentVariableHelper.getEnvironmentVariable("AUTHENTICATION_DATABASE");
    String username = EnvironmentVariableHelper.getEnvironmentVariable("USERNAME");
    String password = EnvironmentVariableHelper.getEnvironmentVariable("PASSWORD");

    MongoDbService mongoDbService = new DefaultMongoDbService(
        new ConnectionString(connectionString),
        MongoCredential.createCredential(username, authenticationDatabase, password.toCharArray())
    );

    getServer().getServicesManager().register(MongoDbService.class, mongoDbService, this, ServicePriority.Normal);
  }
}
