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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 11/2/15.
 */
@Named("numericalCalculator")
@SessionScoped
@Getter
@Setter
public class MyFancyShmancyCalculatorService implements Serializable {

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
    private List<Command> issuedCommands = new ArrayList<>();

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private int commandPosition = 0;

    private String[] operators = new String[]{"+", "-", "*", "/"};

    public void calculate() throws NamingException {
        // cut off list of undo/redo
        if(commandPosition > 1) {
            issuedCommands = issuedCommands.subList(0, commandPosition);
            canRedo = false;
        }
        command = InitialContext.doLookup("java:module/CalculatorCommand");
        command.initialize(calculator, operator.toCharArray()[0], value);
        command.execute();
        issuedCommands.add(command);
        commandPosition++;
        state = calculator.getCalculatorState();
    }

    public void redo(int levels) {
        while(levels > 0){
            if(commandPosition < issuedCommands.size()){
                issuedCommands.get(commandPosition++).execute();
            }
            levels--;
        }
        state = calculator.getCalculatorState();
        if(commandPosition == issuedCommands.size()) {
            canRedo = false;
        }
    }

    public void undo(int levels) {
        while(levels > 0){
            if(commandPosition > 0){
                canRedo = true;
                issuedCommands.get(--commandPosition).undo();
            }
            levels--;
        }
        state = calculator.getCalculatorState();
    }

}
