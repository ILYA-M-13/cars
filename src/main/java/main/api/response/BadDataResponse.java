package main.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BadDataResponse {
    private String error;
    @JsonProperty("error_description")
    private String description;
}
