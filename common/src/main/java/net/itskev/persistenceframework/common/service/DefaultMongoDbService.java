package net.itskev.persistenceframework.common.service;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import net.itskev.persistenceframework.common.api.MongoDbService;
import net.itskev.persistenceframework.common.util.CustomObjectMapper;
import org.bson.UuidRepresentation;
import org.bson.codecs.UuidCodec;
import org.bson.codecs.configuration.CodecRegistries;
import org.mongojack.JacksonMongoCollection;
import org.mongojack.internal.MongoJackModule;

import java.util.concurrent.TimeUnit;

public class DefaultMongoDbService implements MongoDbService {

  private final ConnectionString connectionString;
  private final MongoCredential mongoCredential;
  private MongoClient mongoClient;

  public DefaultMongoDbService(ConnectionString connectionString, MongoCredential mongoCredential) {
    this.connectionString = connectionString;
    this.mongoCredential = mongoCredential;
    connect();
  }

  public DefaultMongoDbService(ConnectionString connectionString) {
    this(connectionString, null);
  }

  @Override
  public <T> JacksonMongoCollection<T> getCollection(String databaseName, String collectionName, Class<T> type) {
    return JacksonMongoCollection.builder()
        .withObjectMapper(MongoJackModule.configure(CustomObjectMapper.create()))
        .build(mongoClient, databaseName, collectionName, type, UuidRepresentation.STANDARD);
  }

  private void connect() {
    MongoClientSettings.Builder mongoClientSettingsBuilder = MongoClientSettings.builder()
        .codecRegistry(CodecRegistries.fromRegistries(
            CodecRegistries.fromCodecs(new UuidCodec(UuidRepresentation.STANDARD)),
            CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry())
        ))
        .applyToSocketSettings(builder -> builder.connectTimeout(10, TimeUnit.SECONDS).build())
        .applyConnectionString(connectionString)
        .uuidRepresentation(UuidRepresentation.STANDARD);

    if (mongoCredential != null) {
      mongoClientSettingsBuilder.credential(mongoCredential);
    }

    mongoClient = MongoClients.create(mongoClientSettingsBuilder.build());
  }
}
