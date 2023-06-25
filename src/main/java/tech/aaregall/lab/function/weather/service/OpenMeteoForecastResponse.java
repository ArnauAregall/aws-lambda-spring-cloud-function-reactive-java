package tech.aaregall.lab.function.weather.service;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDateTime;
import java.util.List;

public record OpenMeteoForecastResponse(
        Float latitude,
        Float longitude,
        OpenMeteoHourlyForecast hourly
) {

    public record OpenMeteoHourlyForecast(
            List<LocalDateTime> time,

            @JsonAlias("temperature_2m")
            List<Float> temperature,

            List<Float> precipitation
    ) {}
}
