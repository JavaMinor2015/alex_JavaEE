package JavaEE7.Calculator.domain;

import JavaEE7.Calculator.domain.abs.Command;
import JavaEE7.Calculator.logic.Calculator;
import lombok.Getter;
import lombok.Setter;

import javax.ejb.Stateful;

/**
 * Created by alex on 11/2/15.
 */
@Getter
@Setter
@Stateful
public class CalculatorCommand implements Command {

    private Calculator calculator;
    private char operator;
    private double value;

    @Override
    public void initialize(Calculator calculator, char operator, double value) {
        this.calculator = calculator;
        this.operator = operator;
        this.value = value;
    }

    @Override
    public void execute() {
        calculator.calculate(operator,value);
    }

    @Override
    public void undo() {
        calculator.calculate(undo(operator),value);
    }

    @Override
    public void remove() {
        this.calculator = null; // die, die!
    }

    private char undo(final char operator) {
        switch (operator) {
            case '+' : return '-';
            case '-' : return '+';
            case '*' : return '/';
            case '/' : return '*';
        }
        return '?';
    }
}
