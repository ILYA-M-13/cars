package main.service;

import main.api.request.CarRequest;
import main.api.response.CarResponse;
import main.api.response.Response;
import main.api.response.StatisticsResponse;
import main.model.AgeOfCar;
import main.model.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {
    Car getCar(String number);
    CarResponse getAllCars(String type, String color, String brand, String year, AgeOfCar ageOfCar);
    StatisticsResponse getAllStatistics();
    Response addCar(CarRequest carRequest);
    Response delCar(String number);
}
