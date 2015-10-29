package JavaEE7.Guesstimate.io;

import JavaEE7.Guesstimate.QuestionParser;
import JavaEE7.Guesstimate.QuestionRepository;
import JavaEE7.Guesstimate.domain.Question;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 10/27/15.
 */
public class FileLoader {
    public void load() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("questions.txt").getFile());

        List<Question> questions = new ArrayList<>();
        QuestionParser parser = new QuestionParser();
        try {
                Files.lines(file.toPath()).forEach(line -> {
                    questions.add(parser.parse(line));
                });
        } catch (IOException e) {
            e.printStackTrace();
        }

        QuestionRepository.setQuestionList(questions);
    }
}
