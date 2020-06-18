package org.edeoliveira.hibernate.demo.tests.annotations;

import org.edeoliveira.hibernate.demo.PersistenceJPAConfig;
import org.edeoliveira.hibernate.demo.model.Book;
import org.edeoliveira.hibernate.demo.model.BookBuilder;
import org.edeoliveira.hibernate.demo.repository.BookRepository;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceJPAConfig.class}, loader = AnnotationConfigContextLoader.class)
@Transactional
public class BasicHibernateTest {
    @Resource
    private BookRepository bookRepository;

    @Resource
    private DataSource dataSource;
    
    @Before
    public void init() throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement stat = connection.createStatement();
        stat.execute("CREATE SEQUENCE IF NOT EXISTS SEQ_VERSION");
        connection.close();
    }

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