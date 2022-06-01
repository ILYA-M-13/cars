package main.service;

import lombok.AllArgsConstructor;
import main.api.request.CarRequest;
import main.api.response.CarResponse;
import main.api.response.StatisticsResponse;
import main.exception.WrongNumberException;
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
    public Car getCar(String number) throws WrongNumberException {
        Optional<Car> oc = carRepo.findCarByNumber(number);
        if (oc.isPresent()) {
            return oc.get();
        } else throw new WrongNumberException("car with this number is not exist");
    }

    @Override
    public StatisticsResponse getAllStatistics() {
        return carRepo.findAllStatistics();
    }

    @Override
    public Car addCar(CarRequest carRequest) throws WrongNumberException {
        String number = carRequest.getNumber();
        String brand = carRequest.getCarBrand();
        String color = carRequest.getColor();
        String type = carRequest.getType();
        String yearOfManufacture = carRequest.getYearOfManufacture();

        if (!isCarNumber(number)) {
            throw new WrongNumberException("incorrect number");
        }
        if (carRepo.findCarByNumber(number).isPresent()) {
            throw new WrongNumberException("car is exist already");
        } else {
            Car car = new Car();
            car.setNumber(number);
            car.setCarBrand(brand);
            car.setYearOfManufacture(yearOfManufacture);
            car.setColor(color);
            car.setType(type.equals("track") || type.equals("cargo") ? TypeOfCar.truck : TypeOfCar.passenger);
            car.setRegTime(new Date());

            return carRepo.save(car);

        }
    }

    @Override
    public boolean delCar(String number) throws WrongNumberException {

        if (number == null || carRepo.findCarByNumber(number).isEmpty()) {
            throw new WrongNumberException("auto is exist already");
        } else {
            carRepo.deleteCarByNumber(number);
            return true;
        }
    }

    private boolean isCarNumber(String number) {
        return number.matches("[а-яА-Яa-zA-Z]\\d{3}[а-яА-Яa-zA-Z]{2}\\d{2,3}[a-zA-Z]{3}");

    }
}


