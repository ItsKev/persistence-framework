package net.itskev.persistenceframework.common.api;

import org.mongojack.JacksonMongoCollection;

public interface MongoDbService {

  /**
   * Returns a {@link JacksonMongoCollection<T>} for a given class.
   *
   * @param databaseName   The database where the collection is stored in
   * @param collectionName The name of the collection where the POJO's are stored
   * @param type           The type of the POJO
   */
  <T> JacksonMongoCollection<T> getCollection(String databaseName, String collectionName, Class<T> type);
}
