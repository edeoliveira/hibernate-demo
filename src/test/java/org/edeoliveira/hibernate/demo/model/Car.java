package org.edeoliveira.hibernate.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CAR")
public class Car extends Vehicle {

    @Column
    private Integer seats;

    @Column(nullable = false)
    private int trunkVolume;

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public int getTrunkVolume() {
        return trunkVolume;
    }

    public void setTrunkVolume(int trunkVolume) {
        this.trunkVolume = trunkVolume;
    }
}
