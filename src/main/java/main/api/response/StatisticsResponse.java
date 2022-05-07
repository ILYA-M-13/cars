package main.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsResponse {

    private long totalCount;
    private Date firstAdd;
    private Date lastAdd;
}
