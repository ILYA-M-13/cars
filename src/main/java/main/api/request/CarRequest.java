package main.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarRequest {
    private String number;
    private String carBrand;
    private String yearOfManufacture;
    private String color;
    private String type;
}
