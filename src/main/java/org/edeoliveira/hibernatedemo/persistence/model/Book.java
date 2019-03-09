package org.edeoliveira.hibernatedemo.persistence.model;

import org.edeoliveira.hibernatedemo.persistence.hibernate.annotation.GenerateVersion;
import org.edeoliveira.hibernatedemo.persistence.hibernate.annotation.IVersionable;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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
                    @Parameter(name = SEQUENCE_PARAM, value = "SEQ_VERSION"),
                    @Parameter(name = INITIAL_PARAM, value = "1"),
                    @Parameter(name = INCREMENT_PARAM, value = "50")
            })
    @Column
    private Long version;

    @SequenceGenerator(name = "minorGenerator", sequenceName = "SEQ_MINOR_VERSION", allocationSize = 1)
    @GeneratedValue(generator = "minorGenerator")
    private Long minorVersion;

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

    public Long getMinorVersion() {
        return minorVersion;
    }

    public void setMinorVersion(Long minorVersion) {
        this.minorVersion = minorVersion;
    }
}
