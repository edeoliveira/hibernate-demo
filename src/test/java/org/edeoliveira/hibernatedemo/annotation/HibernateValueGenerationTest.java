package org.edeoliveira.hibernatedemo.annotation;

import org.edeoliveira.hibernatedemo.persistence.model.Book;
import org.edeoliveira.hibernatedemo.persistence.model.BookBuilder;
import org.edeoliveira.hibernatedemo.persistence.repository.BookRepository;
import org.edeoliveira.hibernatedemo.spring.PersistenceJPAConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceJPAConfig.class}, loader = AnnotationConfigContextLoader.class)
@Transactional
public class HibernateValueGenerationTest {
    @Resource
    private BookRepository bookRepository;

    @Resource
    private DataSource dataSource;

    @Before
    public void init() throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement stat = connection.createStatement();
        stat.execute("CREATE SEQUENCE IF NOT EXISTS SEQ_VERSION");
        stat.execute("CREATE SEQUENCE IF NOT EXISTS SEQ_MINOR_VERSION");
        connection.close();
    }

    @Test
    public void givenBookWithoutVersion_whenSave_thenVersionIsSet() {
        Book book = BookBuilder.create()
                        .withPublicationDate(LocalDateTime.now())
                        .withTitle("Testing hibernate")
                        .build();
        bookRepository.save(book);

        List<Book> books = bookRepository.findAll();
        assertEquals("size incorrect", 1, books.size());
        assertNotNull("version is not set", books.get(0).getVersion());
        assertNull("minor version is set", books.get(0).getMinorVersion());
    }

    @Test
    public void givenBookWithVersion_whenSave_thenVersionIsNotModified() {
        long version = 123L;
        Book book = BookBuilder.create()
                .withPublicationDate(LocalDateTime.now())
                .withTitle("Testing hibernate")
                .withVersion(version)
                .build();
        bookRepository.save(book);

        Optional<Book> optionalBook = bookRepository.findByVersion(version);
        assertTrue(optionalBook.isPresent());
        assertNull("minor version is set", optionalBook.get().getMinorVersion());
    }
}