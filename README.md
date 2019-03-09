# hibernate-demo
Playground project to test some JPA/Hibernate advanced code

### Clone

```
git clone https://github.com/edeoliveira/hibernate-demo.git
```

### Build

Build using Java 1.8 or later, and use your favorite tool Gradle or Maven

### Noticeable demos

- [VersionGenerator](https://github.com/edeoliveira/hibernate-demo/blob/master/src/main/java/org/edeoliveira/hibernatedemo/persistence/hibernate/annotation/GenerateVersion.java) : Demonstrates how to use a sequence generator on a non @Id column using [hibernate Integrator spi](https://github.com/edeoliveira/hibernate-demo/blob/master/src/main/java/org/edeoliveira/hibernatedemo/persistence/hibernate/MetadataExtractorIntegrator.java)
