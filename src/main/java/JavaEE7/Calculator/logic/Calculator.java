package JavaEE7.Calculator.logic;

import lombok.Getter;

import javax.ejb.Stateful;

/**
 * Created by alex on 11/2/15.
 */
@Stateful
public class Calculator {
    @Getter
    private double calculatorState = 0;

    public void calculate(final char operator, final double value) {
        switch (operator) {
            case '-':
                calculatorState -= value;
                break;
            case '+':
                calculatorState += value;
                break;
            case '*':
                calculatorState *= value;
                break;
            case '/':
                calculatorState /= value;
                break;
        }
    }

    public void clear(){
        calculatorState = 0;
    }
}
