package org.edeoliveira.hibernate.demo.repository;

import org.edeoliveira.hibernate.demo.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByBrand(String brand);
}