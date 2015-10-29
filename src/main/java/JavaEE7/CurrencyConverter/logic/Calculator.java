package JavaEE7.CurrencyConverter.logic;

import JavaEE7.CurrencyConverter.CurrencyRepository;
import JavaEE7.CurrencyConverter.domain.Currency;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

/**
 * Created by alex on 10/26/15.
 */
@Named
@ApplicationScoped
public class Calculator {
    @Getter
    @Setter
    private double amount = 1;


    // TODO write custom converter to directly use object in JSF
    @Getter
    @Setter
    private String fromCurrency =  "USD";

    public double convert(final Currency to) {
        return CurrencyRepository.get(fromCurrency).calculate(amount, to);
    }

    public double inverseConvert(final Currency from) {
        return from.calculate(amount, CurrencyRepository.get(fromCurrency));
    }

    public void convertAll(AjaxBehaviorEvent event){
        for (Currency currency : CurrencyRepository.getCurrencies()) {
            currency.setValue(convert(currency));
            currency.setInverseValue(inverseConvert(currency));
        }
    }
}
