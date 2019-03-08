package org.edeoliveira.hibernatedemo.persistence.hibernate.annotation;

import org.hibernate.tuple.AnnotationValueGeneration;
import org.hibernate.tuple.GenerationTiming;
import org.hibernate.tuple.ValueGenerator;

import java.util.Arrays;
import java.util.Properties;

public class VersionGeneration implements AnnotationValueGeneration<GenerateVersion> {
    private final VersionGenerator generator = new VersionGenerator();

    @Override
    public void initialize(GenerateVersion annotation, Class<?> propertyType) {
        Properties properties = new Properties();
        Arrays.stream(annotation.parameters())
              .forEach(parameter -> properties.setProperty(parameter.name(), parameter.value()));
        generator.setProperties(properties);
    }

    @Override
    public GenerationTiming getGenerationTiming() {
        return GenerationTiming.INSERT;
    }

    @Override
    public ValueGenerator<?> getValueGenerator() {
        return generator;
    }

    @Override
    public boolean referenceColumnInSql() {
        return false;
    }

    @Override
    public String getDatabaseGeneratedReferencedColumnValue() {
        return null;
    }
}