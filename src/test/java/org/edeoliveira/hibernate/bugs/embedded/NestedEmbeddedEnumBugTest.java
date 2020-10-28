package org.edeoliveira.hibernate.bugs.embedded;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Bug tracked on Hibernate JIRA as HHH-14294
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {NestedEmbeddedEnumBugJPAConfig.class}, loader = AnnotationConfigContextLoader.class)
@Transactional
public class NestedEmbeddedEnumBugTest {
    @Resource
    private PhoneBookRepository phoneBookRepository;

    @Resource
    private DataSource dataSource;

    @Resource
    private EntityManager entityManager;

    private Contact contact;

    @Before
    public void init() throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement stat = connection.createStatement();
        stat.execute("CREATE SEQUENCE IF NOT EXISTS SEQ_PHONE_BOOK");
        connection.close();

        contact = new Contact();
        contact.setName("Billy Jean");
        contact.setPreferredNumber(PhoneType.HOME);
        contact.setPhoneOne(new PhoneNumber("555-1234", PhoneType.HOME));
        contact.setPhoneTwo(new PhoneNumber("555-9999", PhoneType.WORK));

        PhoneBook phoneBook = new PhoneBook();
        phoneBook.setOwner("my self");
        HashSet<Contact> contacts = new HashSet<>();
        contacts.add(contact);
        phoneBook.setContacts(contacts);

        phoneBookRepository.save(phoneBook);
    }

    @Test
    public void givenASavedContact_whenRetrieved_thenEnumsAShouldBeStoredAsStrings() {
        Query query = entityManager.createNativeQuery(
                "SELECT NAME, PREFERRED, PHONE_1_TYPE, PHONE_2_TYPE FROM CONTACT");
        Object[] data = ((List<Object[]>)query.getResultList()).get(0);

        assertEquals("name incorrect", contact.getName(), data[0]);
        assertEquals("preferred phone type incorrect", contact.getPreferredNumber().name(), data[1]);
        assertEquals("phone one type incorrect", contact.getPhoneOne().getPhoneType().name(), data[2]);
        assertEquals("phone two type incorrect", contact.getPhoneTwo().getPhoneType().name(), data[3]);
    }
}