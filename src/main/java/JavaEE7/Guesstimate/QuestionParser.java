package JavaEE7.Guesstimate;

import JavaEE7.Guesstimate.domain.Question;

import java.util.List;

/**
 * Created by alex on 10/27/15.
 */
public class QuestionParser {

    public void parse(final List<String> lines) {
        for (String line : lines) {
            String[] args = line.split("~");
            QuestionRepository.getQuestionList().add(
                    Question.builder()
                            .description(args[0])
                            .answer(Double.valueOf(args[1]))
                            .build()
            );
        }
    }

    public Question parse(final String line) {
        String[] args = line.split("@");
        return Question.builder()
                .description(args[0])
                .answer(Double.valueOf(args[1]))
                .build();
    }
}
