package org.edeoliveira.hibernate.demo.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Version;

import static org.hibernate.id.enhanced.SequenceStyleGenerator.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "TYPE")
@GenericGenerator(name = "vehicleIdGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name = SEQUENCE_PARAM, value = "SEQ_VEHICLE"),
                @Parameter(name = INITIAL_PARAM, value = "1"),
                @Parameter(name = INCREMENT_PARAM, value = "50")
        })
public abstract class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="vehicleIdGenerator")
    private Long Id;

    @Version
    private int version;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String brand;

    @Column
    private String model;

    @Column(nullable = false)
    private long horsepower;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(long horsepower) {
        this.horsepower = horsepower;
    }
}

