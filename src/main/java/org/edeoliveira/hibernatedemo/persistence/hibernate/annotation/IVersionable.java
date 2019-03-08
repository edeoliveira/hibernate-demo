package org.edeoliveira.hibernatedemo.persistence.hibernate.annotation;

public interface IVersionable {
    Long getVersion();

    void setVersion(Long version);
}
