package org.edeoliveira.hibernate.demo.model;

import java.time.LocalDateTime;

public final class BookBuilder {
    private Long Id;
    private String title;
    private LocalDateTime publicationDate;
    private Long version;

    private BookBuilder() {
    }

    public static BookBuilder create() {
        return new BookBuilder();
    }

    public BookBuilder withId(Long Id) {
        this.Id = Id;
        return this;
    }

    public BookBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public BookBuilder withPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
        return this;
    }

    public BookBuilder withVersion(Long version) {
        this.version = version;
        return this;
    }

    public Book build() {
        Book book = new Book();
        book.setId(Id);
        book.setTitle(title);
        book.setPublicationDate(publicationDate);
        book.setVersion(version);
        return book;
    }
}
