# PersistenceFramework

The plugin sets up a connection to a MongoDB. With the help of Jackson its able
to store and work with POJO's directly.

An example can be found in the tests [here](common/src/test/java/net/itskev/persistenceframework/common/service/DefaultMongoDbServiceTest.java).

## Environment variables
| Name  | Description  |
|---|---|
| CONNECTION_STRING | The connection string for MongoDB. Examples can be found [here](https://docs.mongodb.com/manual/reference/connection-string/). |
| AUTHENTICATION_DATABASE | The database where the user is stored. (e.g. admin) |
| USERNAME | The username which the login is done with |
| PASSWORD | The password to the given username |

## Bukkit (Spigot, Paper)
Getting an instance of the [MongoDbService](common/src/main/java/net/itskev/persistenceframework/common/api/MongoDbService.java) is done over the `ServicesManager`
from Bukkit itself. This is done to prevent opening multiple connections to the MongoDB.

```java
MongoDbService mongoDbService = getServer().getServicesManager().load(MongoDbService.class);
```

## Proxy (Waterfall, Bungeecord)
To get an instance of the [MongoDbService](common/src/main/java/net/itskev/persistenceframework/common/api/MongoDbService.java)
you have to get an instance of the [PersistenceFrameworkPlugin](proxy/src/main/java/net/itskev/persistenceframework/proxy/PersistenceFrameworkPlugin.java)
first and get the MongoDbService from there.

```java
PersistenceFrameworkPlugin persistenceFrameworkPlugin =
    (PersistenceFrameworkPlugin) getProxy().getPluginManager().getPlugin("PersistenceFramework");
MongoDbService mongoDbService = persistenceFrameworkPlugin.getMongoDbService();
```
