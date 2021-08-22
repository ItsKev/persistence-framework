package net.itskev.persistenceframework.common.api;

import org.mongojack.JacksonMongoCollection;

public interface MongoDbService {

  /**
   * Returns a {@link JacksonMongoCollection} for a given class. This collection is a type-safe collection to work with
   * POJO's directly.
   *
   * @param databaseName   The database where the collection is stored in
   * @param collectionName The name of the collection where the POJO's are stored
   * @param <T>            The type of the class
   * @param type           The POJO's class
   * @return a {@link JacksonMongoCollection} for the given class.
   */
  <T> JacksonMongoCollection<T> getCollection(String databaseName, String collectionName, Class<T> type);
}
