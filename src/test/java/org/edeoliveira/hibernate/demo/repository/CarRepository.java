package org.edeoliveira.hibernate.demo.repository;

import org.edeoliveira.hibernate.demo.model.Car;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends VehicleRepository {
    List<Car> findBySeats(int nbOfSeats);
}