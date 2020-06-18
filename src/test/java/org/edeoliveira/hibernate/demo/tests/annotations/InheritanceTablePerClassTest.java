package org.edeoliveira.hibernate.demo.tests.annotations;

import org.edeoliveira.hibernate.demo.model.Bike;
import org.edeoliveira.hibernate.demo.model.BikeBuilder;
import org.edeoliveira.hibernate.demo.model.Car;
import org.edeoliveira.hibernate.demo.model.CarBuilder;
import org.edeoliveira.hibernate.demo.model.Vehicle;
import org.edeoliveira.hibernate.demo.repository.BikeRepository;
import org.edeoliveira.hibernate.demo.repository.CarRepository;
import org.edeoliveira.hibernate.demo.repository.VehicleRepository;
import org.edeoliveira.hibernate.demo.PersistenceJPAConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceJPAConfig.class}, loader = AnnotationConfigContextLoader.class)
@Transactional
public class InheritanceTablePerClassTest {
    @Resource
    private CarRepository carRepository;

    @Resource
    private BikeRepository bikeRepository;

    @Resource
    private VehicleRepository vehicleRepository;

    @Resource
    private DataSource dataSource;

    @Before
    public void init() throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement stat = connection.createStatement();
        stat.execute("CREATE SEQUENCE IF NOT EXISTS SEQ_VEHICLE");
        connection.close();
    }

    @Test
    public void givenACarAndABike_whenSaved_thenRetrieveTwoVehicles() {
        Car car = CarBuilder.create()
                .withBrand("Ferrari")
                .withColor("red")
                .withModel("458 Italia")
                .withHorsepower(568)
                .build();

        Bike bike = BikeBuilder.create()
                .withBrand("Harley Davidson")
                .withColor("black")
                .withModel("2019 FAT BOY")
                .withHorsepower(90)
                .withFairing(true)
                .build();

        carRepository.save(car);
        bikeRepository.save(bike);

        List<Vehicle> books = vehicleRepository.findAll();
        assertEquals("size incorrect", 2, books.size());
    }
}