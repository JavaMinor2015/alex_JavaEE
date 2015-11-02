package JavaEE7.Calculator.domain.abs;

import JavaEE7.Calculator.logic.Calculator;

import javax.ejb.Local;

/**
 * Created by alex on 11/2/15.
 */
@Local
public interface Command {
    void initialize(final Calculator calculator, final char operator,final double value);
    void execute();
    void undo();
    void remove();
}