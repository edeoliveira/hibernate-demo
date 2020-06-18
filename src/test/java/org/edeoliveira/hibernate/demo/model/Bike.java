package org.edeoliveira.hibernate.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "BIKE")
public class Bike extends Vehicle {

    @Column(nullable = false)
    private boolean fairing;

    @Column(nullable = false)
    private long weight;

    public boolean isFairing() {
        return fairing;
    }

    public void setFairing(boolean fairing) {
        this.fairing = fairing;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }
}
