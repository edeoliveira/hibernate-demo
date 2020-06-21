package org.edeoliveira.hibernate.bugs.embedded;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OptimisticLock;
import org.hibernate.annotations.Parameter;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

import static org.hibernate.id.enhanced.SequenceStyleGenerator.*;

@Entity
@Table(name = "PHONE_BOOK")
@GenericGenerator(name = "phoneBookIdGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name = SEQUENCE_PARAM, value = "SEQ_PHONE_BOOK"),
                @Parameter(name = INITIAL_PARAM, value = "1"),
                @Parameter(name = INCREMENT_PARAM, value = "1")
        })
public class PhoneBook {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="phoneBookIdGenerator")
    private Long Id;

    @Column(name = "NAME", nullable = false)
    private String owner;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "CONTACT", joinColumns = @JoinColumn(name = "PHONE_BOOK_ID"))
    @OptimisticLock(excluded = true)
    private Set<Contact> contacts = new HashSet<>();

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }
}
