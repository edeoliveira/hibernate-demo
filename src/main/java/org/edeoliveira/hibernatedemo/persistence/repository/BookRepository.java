package org.edeoliveira.hibernatedemo.persistence.repository;

import org.edeoliveira.hibernatedemo.persistence.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByVersion(Long version);
}