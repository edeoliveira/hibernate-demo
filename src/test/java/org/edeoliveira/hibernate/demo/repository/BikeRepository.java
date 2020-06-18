package org.edeoliveira.hibernate.demo.repository;

import org.edeoliveira.hibernate.demo.model.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Long> {
    List<Bike> findByColor(String color);
}