package main.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import main.model.Car;

import java.util.List;

@Data
@AllArgsConstructor
public class CarResponse {
    private List<Car>cars;
}
