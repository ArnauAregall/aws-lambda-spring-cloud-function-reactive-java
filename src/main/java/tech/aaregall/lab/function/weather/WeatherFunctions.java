package tech.aaregall.lab.function.weather;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import tech.aaregall.lab.function.question.domain.Question;
import tech.aaregall.lab.function.weather.domain.Forecast;
import tech.aaregall.lab.function.weather.domain.GeoLocation;
import tech.aaregall.lab.function.weather.service.WeatherService;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class WeatherFunctions {

    @Bean
    public Function<Flux<GeoLocation>, Flux<Forecast>> forecast(WeatherService weatherService) {
        return weatherService::getForecast;
    }

    @Bean
    public Function<Flux<Forecast>, Flux<Question>> forecastToQuestion() {
        return forecastFlux -> forecastFlux.map(forecast -> new Question(List.of(
            String.format("Given the following weather forecast on coordinates %s %s", forecast.geoLocation().latitude(), forecast.geoLocation().longitude()),
            forecast.hourlyForecasts().stream().map(Forecast.HourlyForecast::toString).collect(Collectors.joining("\n")),
            "Write a 1 to 3 sentence summary of the weather forecast in common vocabulary."
        )));
    }

}
