package net.itskev.persistenceframework.common.service;

import com.mongodb.ConnectionString;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import net.itskev.persistenceframework.common.service.model.Animal;
import net.itskev.persistenceframework.common.service.model.Bark;
import net.itskev.persistenceframework.common.service.model.Meow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mongojack.JacksonMongoCollection;

import java.net.InetSocketAddress;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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
    Animal animal = new Animal(name, 9, new Bark(2.4), false, Animal.Gender.FEMALE);

    JacksonMongoCollection<Animal> collection = testee.getCollection("database", "testee", Animal.class);

    // Act
    collection.insertOne(animal);

    // Assert
    Animal persistedAnimal = collection.find(Filters.eq(Animal.Fields.NAME, name)).first();
    assertNotNull(persistedAnimal);
    assertEquals(animal, persistedAnimal);
  }

  @Test
  void shouldDeletePojo() {
    // Arrange
    String name = "Tester";
    Animal animal = new Animal(name, 12, new Meow(5), true, Animal.Gender.MALE);

    JacksonMongoCollection<Animal> collection = testee.getCollection("database", "testee", Animal.class);
    collection.insertOne(animal);

    // Act
    DeleteResult result = collection.deleteOne(Filters.eq(Animal.Fields.NAME, name));

    // Assert
    assertEquals(1, result.getDeletedCount());
    Animal persistedAnimal = collection.find(Filters.eq(Animal.Fields.NAME, name)).first();
    assertNull(persistedAnimal);
  }

  @Test
  void shouldDecrementValue() {
    // Arrange
    String name = "IncrementalTestee";
    int initialMeowCount = 6;
    int decrement = 2;
    Animal animal = new Animal(name, 2, new Meow(initialMeowCount), false, Animal.Gender.MALE);

    JacksonMongoCollection<Animal> collection = testee.getCollection("database", "testee", Animal.class);
    collection.insertOne(animal);

    // Act
    collection.updateOne(Filters.eq(Animal.Fields.NAME, name), Updates.inc("behaviour.count", -decrement));

    // Assert
    Animal persistedAnimal = collection.find(Filters.eq(Animal.Fields.NAME, name)).first();
    assertNotNull(persistedAnimal);
    assertEquals(initialMeowCount - decrement, ((Meow) persistedAnimal.getBehaviour()).getCount());
  }
}
