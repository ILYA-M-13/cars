package main.service;

import lombok.AllArgsConstructor;
import main.api.request.CarRequest;
import main.api.response.CarResponse;
import main.api.response.Response;
import main.api.response.StatisticsResponse;
import main.model.AgeOfCar;
import main.model.Car;
import main.model.TypeOfCar;
import main.repository.CarRepo;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@AllArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepo carRepo;


    @Override
    public CarResponse getAllCars(String type, String color, String brand, String year, AgeOfCar ageOfCar) {
        List<Car> cars = new ArrayList<>();
        if (ageOfCar != null && year == null) {
            switch (ageOfCar) {
                case older:
                    cars = carRepo.findOlderCarByParam(type, color, brand);
                    break;
                case younger:
                    cars = carRepo.findYoungerCarByParam(type, color, brand);
                    break;
            }
        } else cars = carRepo.findCarByParam(type, color, brand, year);
        return new CarResponse(cars);
    }

    @Override
    public Car getCar(String number) {
        return carRepo.findCarByNumber(number).orElse(new Car());
    }

    @Override
    public StatisticsResponse getAllStatistics() {
        StatisticsResponse sr = carRepo.findAllStatistics();
        return sr;
    }

    @Override
    public Response addCar(CarRequest carRequest) {
        Response response = new Response();
        Map<String, String> err = new HashMap<>();
        String number = carRequest.getNumber();
        String brand = carRequest.getCarBrand();
        String color = carRequest.getColor();
        String type = carRequest.getType();
        String yearOfManufacture = carRequest.getYearOfManufacture();

        if (!isCarNumber(number)) {
            err.put("number", "Номер авто введен не верно!");
            response.setErrors(err);
            return response;
        }
        if (carRepo.findCarByNumber(number).isPresent()) {
            err.put("number", "Авто с таким номером уже существует!");
            response.setErrors(err);
            return response;
        }

        Car car = new Car();
        car.setNumber(number);
        car.setCarBrand(brand);
        car.setYearOfManufacture(yearOfManufacture);
        car.setColor(color);
        car.setType(type.equals("track") || type.equals("cargo") ? TypeOfCar.truck : TypeOfCar.passenger);
        car.setRegTime(new Date());

        carRepo.save(car);
        response.setResult(true);
        return response;
    }

    @Override
    public Response delCar(String number) {
        Response response = new Response();
        Map<String, String> err = new HashMap<>();

        if (number == null || carRepo.findCarByNumber(number).isEmpty()) {
            err.put("number", "Нет авто с таким номером!");
            response.setErrors(err);
            return response;
        }

        carRepo.deleteCarByNumber(number);
        response.setResult(true);
        return response;
    }

    private boolean isCarNumber(String number) {
        return number.matches("[а-яА-Яa-zA-Z]\\d{3}[а-яА-Яa-zA-Z]{2}\\d{2,3}[a-zA-Z]{3}");

    }
}


