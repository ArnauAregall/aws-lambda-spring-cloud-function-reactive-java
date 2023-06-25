package tech.aaregall.lab.function.weather.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Flux;

@Component
public class OpenMeteoClient {

    private final OpenMeteoHttpClient openMeteoHttpClient;

    public OpenMeteoClient(WebClient.Builder webClientBuilder) {
        this.openMeteoHttpClient = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(
                        webClientBuilder
                                .baseUrl("https://api.open-meteo.com/v1/")
                                .build()
                ))
                .build()
                .createClient(OpenMeteoHttpClient.class);
    }

    public Flux<OpenMeteoForecastResponse> getForecast(Float latitude, Float longitude) {
        return openMeteoHttpClient.getForecast(latitude, longitude, "temperature_2m,precipitation");
    }

    private interface OpenMeteoHttpClient {

        @GetExchange("/forecast")
        Flux<OpenMeteoForecastResponse> getForecast(
                @RequestParam("latitude") Float latitude,
                @RequestParam("longitude") Float longitude,
                @RequestParam("hourly") String hourly
        );
    }

}
