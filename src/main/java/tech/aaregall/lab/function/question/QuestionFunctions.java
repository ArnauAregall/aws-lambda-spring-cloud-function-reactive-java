package tech.aaregall.lab.function.question;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import tech.aaregall.lab.function.question.domain.Answer;
import tech.aaregall.lab.function.question.domain.Question;

import java.util.function.Function;

@Configuration
public class QuestionFunctions {

    @Bean
    public Function<Flux<Question>, Flux<Answer>> question() {
        return questionFlux -> questionFlux
                .map(question -> new Answer(String.format("Answer from Flux: Question %s", question)));
    }

}
