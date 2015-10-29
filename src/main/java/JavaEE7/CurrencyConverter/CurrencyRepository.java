package JavaEE7.CurrencyConverter;

import JavaEE7.CurrencyConverter.domain.Currency;
import JavaEE7.CurrencyConverter.logic.Calculator;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.*;

/**
 * Created by alex on 10/26/15.
 */
@Named
@ApplicationScoped
public class CurrencyRepository {

    @Getter
    @Setter
    private static List<Currency> currencies = new ArrayList<>();

    static {
//        Currency[] cur = new Currency[]{
//                new Currency("EUR", 0.88, 1.14),
//                new Currency("USD", 1, 1),
//                new Currency("GBP", 0.65, 1.55),
//                new Currency("AUD", 0.72, 1.3759)
//        };
//        for (Currency currency : cur) {
//            currencies.add(currency);
//        }
//        new Calculator().convertAll(null);
    }


    public static Currency get(final String fromCurrency) {
        for (Currency c : currencies) {
            if (c.getCode().equals(fromCurrency)) {
                return c;
            }
        }
        return (Currency) currencies.toArray()[0];
    }

    public static void delete(final Currency currency) {
        currencies.remove(currency);
    }

    public static void edit(final Currency currency) {
        currency.setEdited(true);
    }

    public static void save() {
        currencies.forEach(c -> c.setEdited(false));
        new Calculator().convertAll(null);
    }

    public static void add() {
        currencies.add(Currency.builder()
                .code("")
                .edited(true)
                .build());
    }

    // TODO better sorting methods

    public String sortByName(final String dir) {
        Collections.sort(currencies, new Comparator<Currency>() {
            @Override
            public int compare(Currency key_1, Currency key_2) {
                if (dir.equals("asc")) {
                    return key_1.getCode().compareTo(key_2.getCode());
                } else {
                    return key_2.getCode().compareTo(key_1.getCode());
                }
            }
        });
        return null;
    }

    public String sortByValue(final String dir) {
        Collections.sort(currencies, new Comparator<Currency>() {
            @Override
            public int compare(Currency key_1, Currency key_2) {
                if (dir.equals("asc")) {
                    return Double.valueOf(key_1.getValue()).compareTo(key_2.getValue());
                } else {
                    return Double.valueOf(key_2.getValue()).compareTo(key_1.getValue());
                }
            }
        });
        return null;
    }

    public String sortByInverseValue(final String dir) {
        Collections.sort(currencies, new Comparator<Currency>() {
            @Override
            public int compare(Currency key_1, Currency key_2) {
                if (dir.equals("asc")) {
                    return Double.valueOf(key_1.getInverseValue()).compareTo(key_2.getInverseValue());
                } else {
                    return Double.valueOf(key_2.getInverseValue()).compareTo(key_1.getInverseValue());
                }
            }
        });
        return null;
    }

    public String sortByValueInDollars(final String dir) {
        Collections.sort(currencies, new Comparator<Currency>() {
            @Override
            public int compare(Currency key_1, Currency key_2) {
                if (dir.equals("asc")) {
                    return Double.valueOf(key_1.getValueInDollars()).compareTo(key_2.getValueInDollars());
                } else {
                    return Double.valueOf(key_2.getValueInDollars()).compareTo(key_1.getValueInDollars());
                }
            }
        });
        return null;
    }
}
