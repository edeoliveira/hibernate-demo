package org.edeoliveira.hibernate.demo.repository;

import org.edeoliveira.hibernate.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByVersion(Long version);
}