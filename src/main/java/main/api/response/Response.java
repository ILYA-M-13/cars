package main.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Map;

@Data
public class Response {
    private boolean result;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> errors;
}
