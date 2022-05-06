package main.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String number;
    @Column(nullable = false)
    private String carBrand;
    @Column(nullable = false)
    private String color;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeOfCar type;
    @Column(name = "reg_time", columnDefinition = "datetime", nullable = false)
    private Date regTime;
}
