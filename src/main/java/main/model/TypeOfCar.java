package main.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TypeOfCar {
    truck("грузовой"),
    passenger("легковой");
    private final String type;
}
