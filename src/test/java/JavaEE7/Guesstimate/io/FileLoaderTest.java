package JavaEE7.Guesstimate.io;

import JavaEE7.Guesstimate.QuestionRepository;
import JavaEE7.Guesstimate.domain.Question;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by alex on 10/27/15.
 */
public class FileLoaderTest {

    @Test
    public void testLoad() throws Exception {
        new FileLoader().load();
        List<Question> list = QuestionRepository.getQuestionList();
        assertThat(list.size(), is(3));
        assertThat(list.get(0).getDescription(), is("Hoe hoog is de eifeltoren in meters? "));
    }
}