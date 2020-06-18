package org.edeoliveira.hibernate.demo.model;

public final class BikeBuilder {
    private Long Id;
    private int version;
    private String color;
    private String brand;
    private String model;
    private long horsepower;
    private boolean fairing;
    private long weight;

    private BikeBuilder() {
    }

    public static BikeBuilder create() {
        return new BikeBuilder();
    }

    public BikeBuilder withId(Long Id) {
        this.Id = Id;
        return this;
    }

    public BikeBuilder withVersion(int version) {
        this.version = version;
        return this;
    }

    public BikeBuilder withColor(String color) {
        this.color = color;
        return this;
    }

    public BikeBuilder withBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public BikeBuilder withModel(String model) {
        this.model = model;
        return this;
    }

    public BikeBuilder withHorsepower(long horsepower) {
        this.horsepower = horsepower;
        return this;
    }

    public BikeBuilder withFairing(boolean fairing) {
        this.fairing = fairing;
        return this;
    }

    public BikeBuilder withWeight(long weight) {
        this.weight = weight;
        return this;
    }

    public Bike build() {
        Bike bike = new Bike();
        bike.setId(Id);
        bike.setVersion(version);
        bike.setColor(color);
        bike.setBrand(brand);
        bike.setModel(model);
        bike.setHorsepower(horsepower);
        bike.setWeight(weight);
        bike.setFairing(fairing);
        return bike;
    }
}
