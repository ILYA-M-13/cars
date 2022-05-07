package main.controller;

import lombok.RequiredArgsConstructor;
import main.api.request.CarRequest;
import main.api.response.CarResponse;
import main.api.response.Response;
import main.api.response.StatisticsResponse;
import main.model.AgeOfCar;
import main.model.Car;
import main.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiCarsController {

    private final CarService carService;

    @GetMapping("/search")
    public ResponseEntity<CarResponse> getAllCars(@RequestParam(value = "ageOfCar", required = false) AgeOfCar ageOfCar,
                                                  @RequestParam(value = "type", required = false) String type,
                                                  @RequestParam(value = "color", required = false) String color,
                                                  @RequestParam(value = "brand", required = false) String brand,
                                                  @RequestParam(value = "year", required = false) String year) {
        return ResponseEntity.ok(carService.getAllCars(type,color,brand,year,ageOfCar));
    }

    @GetMapping("/car")
    public ResponseEntity<Car> getCarByNumber(@RequestParam String number){
        return ResponseEntity.ok(carService.getCar(number));
    }

    @GetMapping("/stat")
    public ResponseEntity<StatisticsResponse> statistics() {
        return ResponseEntity.ok(carService.getAllStatistics());
    }

    @PostMapping("/add")
    public ResponseEntity<Response> addCar(@RequestBody CarRequest carRequest) {
        return ResponseEntity.ok(carService.addCar(carRequest));
    }

    @DeleteMapping("/del")
    public ResponseEntity<Response> delCar(@RequestParam String number) {
        return ResponseEntity.ok(carService.delCar(number));
    }
}
