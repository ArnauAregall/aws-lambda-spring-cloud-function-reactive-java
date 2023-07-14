package tech.aaregall.lab.function.weather.domain;

import java.time.LocalDateTime;
import java.util.List;

public record Forecast(GeoLocation geoLocation, List<HourlyForecast> hourlyForecasts) {

    public record HourlyForecast(
            LocalDateTime time,
            Float temperature,
            Float precipitation) {

        @Override
        public String toString() {
            return String.format("At %s, temperature will be %.2f º, precipitation of %.2f mm",
                    time.toString(), temperature, precipitation);
        }
    }
}
