package main.service;

import main.api.request.CarRequest;
import main.api.response.CarResponse;
import main.api.response.StatisticsResponse;
import main.exception.WrongNumberException;
import main.model.AgeOfCar;
import main.model.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {
    Car getCar(String number) throws WrongNumberException;
    CarResponse getAllCars(String type, String color, String brand, String year, AgeOfCar ageOfCar);
    StatisticsResponse getAllStatistics();
    Car addCar(CarRequest carRequest) throws WrongNumberException;
    boolean delCar(String number) throws WrongNumberException;
}
