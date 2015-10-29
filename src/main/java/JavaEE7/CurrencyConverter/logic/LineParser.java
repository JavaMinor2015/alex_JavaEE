package JavaEE7.CurrencyConverter.logic;

import JavaEE7.CurrencyConverter.CurrencyRepository;
import JavaEE7.CurrencyConverter.domain.Currency;

import java.util.List;

/**
 * Created by alex on 10/27/15.
 */
public class LineParser {

    public void parse(final List<String> lines) {
        for (String line : lines) {
            String[] args = null;
            if(isValid(line)) {
                args = line.split(",");
            }
            // EUR, 0.88, 1.14
            Currency c = Currency.builder()
                    .code(args[0])
                    .valueInDollars(Double.valueOf(args[1]))
                    .oneDollarIs(Double.valueOf(args[2]))
                    .build();
            CurrencyRepository.getCurrencies().add(c);
        }
        new Calculator().convertAll(null);
    }

    private boolean isValid(final String line) {
        if(!line.contains(",")) {
            return false;
        }
        // TODO implement
        return true;
    }
}
