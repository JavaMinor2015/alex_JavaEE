package JavaEE7.Guesstimate;

import JavaEE7.Guesstimate.domain.Question;
import JavaEE7.Guesstimate.io.FileLoader;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by alex on 10/27/15.
 */
@Named
@ApplicationScoped
@Getter
@Setter
public class QuestionRepository {

    @Getter
    @Setter
    private static List<Question> questionList;
    private List<Question> answeredList = new ArrayList<>();

    private double answer;
    private Question question;

    static {
        new FileLoader().load();
    }

    public void getNextRandomQuestion() {
        if (questionList.isEmpty()) {
            calculateScore();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Starting a new game!"));
            new FileLoader().load();
            answeredList.clear();

        }
        question = questionList.get(new Random().nextInt(questionList.size()));
    }

    public void answerQuestion() {
        if (question != null) {
            if(!answeredList.contains(question)) {
                questionList.remove(question);
                question.setProvidedAnswer(answer);
                answeredList.add(question);
                calculateScore();
            }
        }
    }

    private void calculateScore() {
        if (answeredList.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Nog geen score."));
            return;
        }
        double points = 0;
        for (Question q : answeredList) {
            points += q.getScore();
        }
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Huidige vraag: " + Math.round(question.getScore()) + " / " + "totaal: " + Math.round(points)));
    }


}
