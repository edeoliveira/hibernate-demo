package org.edeoliveira.hibernatedemo.persistence.model;

import org.edeoliveira.hibernatedemo.persistence.hibernate.annotation.GenerateVersion;
import org.edeoliveira.hibernatedemo.persistence.hibernate.annotation.IVersionable;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

import static org.hibernate.id.enhanced.SequenceStyleGenerator.*;

@Entity
public class Book implements IVersionable {

    @Id
    @GeneratedValue
    private Long Id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDateTime publicationDate;

    @GenerateVersion(
            parameters = {
                    @Parameter(name = SEQUENCE_PARAM, value = "SEQ_VERSION_GENERATOR"),
                    @Parameter(name = INITIAL_PARAM, value = "1"),
                    @Parameter(name = INCREMENT_PARAM, value = "50")
            })
    @Column
    private Long version;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
