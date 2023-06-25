package tech.aaregall.lab.function.weather;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import tech.aaregall.lab.function.weather.domain.Forecast;
import tech.aaregall.lab.function.weather.domain.GeoLocation;
import tech.aaregall.lab.function.weather.service.WeatherService;

import java.util.function.Function;

@Configuration
public class WeatherFunctions {

    @Bean
    public Function<Flux<GeoLocation>, Flux<Forecast>> forecast(WeatherService weatherService) {
        return weatherService::getForecast;
    }

}
