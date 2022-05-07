package main.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AgeOfCar {

    older("Сначала старые машины"),
    younger("Сначала новые машины");
    private final String type;
}
