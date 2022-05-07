package main.service;

import main.api.request.CarRequest;
import main.api.response.Response;
import main.model.Car;
import main.repository.CarRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @InjectMocks
    CarServiceImpl carService;
    @Mock
    CarRepo carRepo;

    private CarRequest incorrectRequest;
    private CarRequest correctRequest;
    private String correctNumber = "A987AA42RUS";

    @BeforeEach
    void setUp() {
        incorrectRequest = new CarRequest();
        incorrectRequest.setNumber("er45g5");
        incorrectRequest.setCarBrand("test");
        incorrectRequest.setType("test");
        incorrectRequest.setColor("test");
        incorrectRequest.setYearOfManufacture("test");

        correctRequest = new CarRequest();
        correctRequest.setNumber(correctNumber);
        correctRequest.setCarBrand("test");
        correctRequest.setType("test");
        correctRequest.setColor("test");
        correctRequest.setYearOfManufacture("test");

    }

    @Test
    void testAddCarWithIncorrectNumber() {
        Response response = carService.addCar(incorrectRequest);
        assertEquals(response.getErrors().get("number"), "Номер авто введен не верно!");
    }

    @Test
    void testAddCarWithCorrectExistingNumber() {
        Mockito.when(carRepo.findCarByNumber(Mockito.any())).thenReturn(Optional.of(new Car()));
        Response response = carService.addCar(correctRequest);
        assertEquals(response.getErrors().get("number"), "Авто с таким номером уже существует!");
    }

    @Test
    void testAddCarWithCorrectNumber() {
        Mockito.when(carRepo.findCarByNumber(Mockito.any())).thenReturn(Optional.empty());
        Response response = carService.addCar(correctRequest);
        assertEquals(response.isResult(), true);
    }

    @Test
    void testDelCarWithCorrectNoExistNumber() {
        Mockito.when(carRepo.findCarByNumber(Mockito.any())).thenReturn(Optional.empty());
        Response response = carService.delCar(correctNumber);
        assertEquals(response.getErrors().get("number"), "Нет авто с таким номером!");
    }

    @Test
    void testDelCarWithCorrectExistNumber() {
        Mockito.when(carRepo.findCarByNumber(Mockito.any())).thenReturn(Optional.of(new Car()));
        Response response = carService.delCar(correctNumber);
        assertEquals(response.isResult(), true);
    }
}