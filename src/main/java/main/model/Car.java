package main.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String number;

    @Column(name = "car_brand",nullable = false)
    private String carBrand;

    @Column(nullable = false)
    private String color;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('truck', 'passenger') default 'passenger'",
            nullable = false)
    private TypeOfCar type;

    @Column(name = "reg_time", columnDefinition = "datetime", nullable = false)
    private Date regTime;

    @Column(name = "year_of_manufacture",nullable = false)
    private String yearOfManufacture;
}
