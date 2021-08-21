package net.itskev.persistenceframework.common.service;

import com.mongodb.ConnectionString;
import com.mongodb.client.model.Filters;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import net.itskev.persistenceframework.common.service.model.Animal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mongojack.JacksonMongoCollection;

import java.net.InetSocketAddress;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class DefaultMongoDbServiceTest {

  private DefaultMongoDbService testee;

  @BeforeEach
  void setUp() {
    MongoServer mongoServer = new MongoServer(new MemoryBackend());
    InetSocketAddress inetSocketAddress = mongoServer.bind();

    testee = new DefaultMongoDbService(new ConnectionString("mongodb://" + inetSocketAddress.getHostString() + ":"
        + inetSocketAddress.getPort()));
  }

  @Test
  void shouldSavePojo() {
    // Arrange
    String name = "Testee";
    Animal animal = new Animal(name, 9);

    JacksonMongoCollection<Animal> collection = testee.getCollection("database", "testee", Animal.class);

    // Act
    collection.save(animal);

    // Assert
    Animal persistedAnimal = collection.find(Filters.eq(Animal.Fields.NAME, name)).first();
    assertNotNull(persistedAnimal);
    assertEquals(animal, persistedAnimal);
  }
}
