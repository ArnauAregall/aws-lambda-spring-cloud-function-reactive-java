package tech.aaregall.lab.function.weather.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import tech.aaregall.lab.function.weather.domain.Forecast;
import tech.aaregall.lab.function.weather.domain.GeoLocation;

import java.util.function.Function;

@Service
public class WeatherService {

    private final OpenMeteoClient openMeteoClient;

    public WeatherService(OpenMeteoClient openMeteoClient) {
        this.openMeteoClient = openMeteoClient;
    }

    public Flux<Forecast> getForecast(Flux<GeoLocation> messageFlux) {
        return messageFlux
                .map(geoLocation -> openMeteoClient.getForecast(geoLocation.latitude(), geoLocation.longitude()))
                .flatMap(openMeteoForecastResponseFlux -> openMeteoForecastResponseFlux.map(responseToForecast));
    }

    private static Function<OpenMeteoForecastResponse, Forecast> responseToForecast = response ->
            new Forecast(new GeoLocation(response.latitude(), response.longitude()),
                    response.hourly().time().stream().map( time -> {
                        var index = response.hourly().time().indexOf(time);
                        return new Forecast.HourlyForecast(time,
                                response.hourly().temperature().get(index),
                                response.hourly().precipitation().get(index));
                        }
                    ).toList()
            );
}
