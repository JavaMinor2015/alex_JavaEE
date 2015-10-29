package JavaEE7.Guesstimate.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by alex on 10/27/15.
 */
@Getter
@Setter
@Builder
public class Question {
    private String description;
    private double answer;
    private double providedAnswer;

    public double getScore() {
        return 100 - Math.min(100, Math.abs((providedAnswer - answer) / answer) * 100);
    }
}
