# spring-boot-kafka-example
Code example of using Kafka with Spring Boot. This demo focuses on a retry mechanism, and is based on Confluent
tutorials like
*["How to Work with Apache Kafka in Your Spring Boot Application"](https://www.confluent.io/blog/apache-kafka-spring-boot-application/)*
and
*["Can Your Kafka Consumers Handle a Poison Pill?"](https://www.confluent.io/blog/spring-kafka-can-your-kafka-consumers-handle-a-poison-pill/)*

## Requirements
- JDK 8+
- Postman 2.1 or newer  
- Kafka running on the default port (9092) with the topics `retry_task` and `error_notification` added.
- Spring Boot app running on port specified in `application.properties` (9000)

Gradle 7 runs in a wrapper, so no need to install or upgrade a global Gradle installation! Just cd into the project
directory and use `gradlew` on Linux/Mac or `gradlew.bat` on Windows instead of `gradle` in your command line.

## Postman project
Import the Postman collection found in the `postman` folder to test the REST API.

## Points of note
- To enable a POJO to be serialized/deserialized:
  - Its package must be designated as trusted in `application.properties`
  - Default no-args and all-args constructors should be present for each POJO  
- If you want to avoid an eternal loop if data is bad or the deserializer is wrong, you must:
  - Use `org.springframework.kafka.support.serializer.ErrorHandlingDeserializer` for both the deserializer key and value
  - Delegate the deserializer key and value classes in `application.properties`
  - Add a `LoggingErrorHandler` bean to the application context
