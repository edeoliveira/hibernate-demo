package org.edeoliveira.hibernate.bugs.embedded;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

//@Entity
//@Table(name = "CONTACT")
//@GenericGenerator(name = "contactIdGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
//        parameters = {
//                @Parameter(name = SEQUENCE_PARAM, value = "SEQ_CONTACT"),
//                @Parameter(name = INITIAL_PARAM, value = "1"),
//                @Parameter(name = INCREMENT_PARAM, value = "1")
//        })
@Embeddable
public class Contact {

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="contactIdGenerator")
//    private Long Id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "PREFERRED", nullable = false)
    private PhoneType preferredNumber;

    @AttributeOverrides({
            @AttributeOverride(name = "number", column = @Column(name = "PHONE_1_NUMBER")),
            @AttributeOverride(name = "phoneType", column = @Column(name = "PHONE_1_TYPE"))
    })
    @Embedded
    private PhoneNumber phoneOne;

    @AttributeOverrides({
            @AttributeOverride(name = "number", column = @Column(name = "PHONE_2_NUMBER")),
            @AttributeOverride(name = "phoneType", column = @Column(name = "PHONE_2_TYPE"))
    })
    @Embedded
    private PhoneNumber phoneTwo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PhoneType getPreferredNumber() {
        return preferredNumber;
    }

    public void setPreferredNumber(PhoneType preferredNumber) {
        this.preferredNumber = preferredNumber;
    }

    public PhoneNumber getPhoneOne() {
        return phoneOne;
    }

    public void setPhoneOne(PhoneNumber phoneOne) {
        this.phoneOne = phoneOne;
    }

    public PhoneNumber getPhoneTwo() {
        return phoneTwo;
    }

    public void setPhoneTwo(PhoneNumber phoneTwo) {
        this.phoneTwo = phoneTwo;
    }
}
