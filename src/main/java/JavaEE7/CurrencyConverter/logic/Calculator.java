package JavaEE7.CurrencyConverter.logic;

import JavaEE7.CurrencyConverter.CurrencyRepository;
import JavaEE7.CurrencyConverter.domain.Currency;
import JavaEE7.CurrencyConverter.interceptor.ExceptionalAmountsLogged;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by alex on 10/26/15.
 */

@Named
@SessionScoped
@ExceptionalAmountsLogged
public class Calculator implements Serializable{
    @Getter
    @Setter
    private double amount = 1;


    // TODO write custom converter to directly use object in JSF
    @Getter
    @Setter
    private String fromCurrency = "USD";

    public double convert(final Currency to) {
        return CurrencyRepository.get(fromCurrency).calculate(amount, to);
    }

    public double inverseConvert(final Currency from) {
        return from.calculate(amount, CurrencyRepository.get(fromCurrency));
    }

    public void convertAll(AjaxBehaviorEvent event) {
        // note, this only to test annotation below since amount is not a variable to this method
        calculateAmount(amount);
    }

    public void calculateAmount(double amount) {
        for (Currency currency : CurrencyRepository.getCurrencies()) {
            currency.setValue(convert(currency));
            currency.setInverseValue(inverseConvert(currency));
        }
    }
}
