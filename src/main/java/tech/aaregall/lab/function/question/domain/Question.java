package tech.aaregall.lab.function.question.domain;

import java.util.List;

public record Question(List<String> messages) {


    @Override
    public String toString() {
        return String.format("[messages='%s']", String.join(",", messages));
    }
}