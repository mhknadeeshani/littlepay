## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.littlepay.transport.pricing.PricingModuleApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
Input file - rides.csv is at the resources directory.

Output file - output.csv will be created in the littlepay directory after successful run.

## Assumptions

Assume at most two stop Ids are traveled by single traveller.
Assume that one traveller will travel only one complete ride.