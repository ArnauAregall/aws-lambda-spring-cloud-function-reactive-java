package tech.aaregall.lab.function;

import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.context.annotation.Configuration;
import tech.aaregall.lab.function.question.domain.Answer;
import tech.aaregall.lab.function.question.domain.Question;
import tech.aaregall.lab.function.weather.domain.Forecast;
import tech.aaregall.lab.function.weather.domain.GeoLocation;
import tech.aaregall.lab.function.weather.service.OpenMeteoForecastResponse;

@Configuration
@RegisterReflectionForBinding({
        GeoLocation.class,
        Forecast.class,
        OpenMeteoForecastResponse.class,
        Question.class,
        Answer.class
})
class NativeConfig {
}
