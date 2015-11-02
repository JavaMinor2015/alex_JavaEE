package JavaEE7.Calculator.service;

import JavaEE7.Calculator.domain.abs.Command;
import JavaEE7.Calculator.logic.Calculator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by alex on 11/2/15.
 */
@Named("numericalCalculator")
@SessionScoped
@Getter
@Setter
public class MyFancierShmancierCalculatorService implements Serializable {

    @EJB
    Command command;
    @EJB
    Calculator calculator;

    private double state = 0;
    private double value = 0;
    private String operator = "+";

    private boolean canRedo = false;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Deque<Command> commands = new ArrayDeque<>();

    private Deque<Command> redoCommands = new ArrayDeque<>();

    private String[] operators = new String[]{"+", "-", "*", "/"};

    public void calculate() throws NamingException {
        // cut off list of undo/redo
        redoCommands.clear();
        canRedo = false;

        command = InitialContext.doLookup("java:module/CalculatorCommand");
        command.initialize(calculator, operator.toCharArray()[0], value);
        command.execute();
        commands.push(command);
        state = calculator.getCalculatorState();
    }

    public void redo(int levels) {
        while (levels > 0) {
            Command c = redoCommands.pop();
            c.execute();
            commands.push(c);
            levels--;
        }
        state = calculator.getCalculatorState();
        if (redoCommands.isEmpty()) {
            canRedo = false;
        }
    }

    public void undo(int levels) {
        if (!commands.isEmpty()) {
            while (levels > 0) {
                canRedo = true;
                Command c = commands.pop();
                c.undo();
                redoCommands.push(c);
                levels--;
            }
        }
        state = calculator.getCalculatorState();
    }
}
