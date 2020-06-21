package org.edeoliveira.hibernate.bugs.embedded;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class PhoneNumber {

    public PhoneNumber() {
    }

    public PhoneNumber(String number, PhoneType phoneType) {
        this.number = number;
        this.phoneType = phoneType;
    }

    private String number;

    @Enumerated(EnumType.STRING)
    private PhoneType phoneType;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    //@Enumerated(EnumType.STRING)
    //Bug doesn't happen if annotation is used on getter instead of field
    public PhoneType getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(PhoneType phoneType) {
        this.phoneType = phoneType;
    }
}
