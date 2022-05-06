package main.controller;

import lombok.RequiredArgsConstructor;
import main.api.request.CarRequest;
import main.api.response.Response;
import main.api.response.StatisticsResponse;
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
    public ResponseEntity<List<Car>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
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
