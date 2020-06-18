package org.edeoliveira.hibernate.demo.annotation;

public interface IVersionnable {
    Long getVersion();

    void setVersion(Long version);
}
