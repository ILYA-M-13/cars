package main.service;

import lombok.AllArgsConstructor;
import main.api.request.CarRequest;
import main.api.response.Response;
import main.api.response.StatisticsResponse;
import main.model.Car;
import main.repository.CarRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarService {

    private final CarRepo carRepo;

    public List<Car> getAllCars() {
        return null;
    }

   public StatisticsResponse getAllStatistics(){
        return null;
   }

   public Response addCar(CarRequest carRequest){
       return null;
   }

    public Response delCar(String number){
        return null;
    }
}
