package main.service;

import main.exception.WrongNumberException;
import main.model.Car;
import main.repository.CarRepo;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @InjectMocks
    CarServiceImpl csi;
    @Mock
    CarRepo carRepo;

    Car car;

    @BeforeEach
    void setUp() {
        car = new Car();
        car.setCarBrand("Toyota");
    }

    @Test
    void getCarWithCorrectNumber() throws WrongNumberException {

        Mockito.when(carRepo.findCarByNumber(Mockito.any())).thenReturn(Optional.of(car));
        Car car = csi.getCar("test");
        assertEquals(car.getCarBrand(), "Toyota");
    }

    @Test
    void getCarWithInCorrectNumber() throws WrongNumberException {

        Mockito.when(carRepo.findCarByNumber(Mockito.any())).thenReturn(Optional.empty());
        try {
            Car car = csi.getCar("test");
            Assert.fail();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "car with this number is not exist");
        }

    }

}