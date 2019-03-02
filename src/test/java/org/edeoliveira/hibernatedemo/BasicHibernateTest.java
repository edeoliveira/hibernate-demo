package org.edeoliveira.hibernatedemo;

import org.edeoliveira.hibernatedemo.persistence.model.Book;
import org.edeoliveira.hibernatedemo.persistence.model.BookBuilder;
import org.edeoliveira.hibernatedemo.persistence.repository.BookRepository;
import org.edeoliveira.hibernatedemo.spring.PersistenceJPAConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceJPAConfig.class}, loader = AnnotationConfigContextLoader.class)
@Transactional
public class BasicHibernateTest {
    @Resource
    private BookRepository bookRepository;

    @Test
    public void givenBook_whenSave_thenCollectionIsNotEmpty() {
        Book book = BookBuilder.create()
                        .withPublicationDate(LocalDateTime.now())
                        .withTitle("New hibernate book")
                        .build();
        bookRepository.save(book);

        List<Book> books = bookRepository.findAll();
        assertEquals("size incorrect", 1, books.size());
    }
}