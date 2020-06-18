package org.edeoliveira.hibernate.demo.annotation;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.ValueGenerationType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@ValueGenerationType(generatedBy = VersionGeneration.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface GenerateVersion {
    Parameter[] parameters() default {};
}