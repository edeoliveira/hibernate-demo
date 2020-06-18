package org.edeoliveira.hibernate.demo.model;

public final class CarBuilder {
    private Long Id;
    private int version;
    private String color;
    private String brand;
    private String model;
    private long horsepower;
    private int seats;
    private int trunkVolume;

    private CarBuilder() {
    }

    public static CarBuilder create() {
        return new CarBuilder();
    }

    public CarBuilder withId(Long Id) {
        this.Id = Id;
        return this;
    }

    public CarBuilder withVersion(int version) {
        this.version = version;
        return this;
    }

    public CarBuilder withColor(String color) {
        this.color = color;
        return this;
    }

    public CarBuilder withBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public CarBuilder withModel(String model) {
        this.model = model;
        return this;
    }

    public CarBuilder withHorsepower(long horsepower) {
        this.horsepower = horsepower;
        return this;
    }

    public CarBuilder withSeats(int seats) {
        this.seats = seats;
        return this;
    }

    public CarBuilder withTrunkVolume(int trunkVolume) {
        this.trunkVolume = trunkVolume;
        return this;
    }

    public Car build() {
        Car car = new Car();
        car.setId(Id);
        car.setVersion(version);
        car.setColor(color);
        car.setBrand(brand);
        car.setModel(model);
        car.setHorsepower(horsepower);
        car.setSeats(seats);
        car.setTrunkVolume(trunkVolume);
        return car;
    }
}
