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

    public static Currency get(final String fromCurrency) {
        for (Currency c : currencies) {
            if (c.getCode().equals(fromCurrency)) {
                return c;
            }
        }
        return currencies.get(0);
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
        Currency c = Currency.builder()
                .code("")
                .build();
        c.setEdited(true);
        currencies.add(c);
    }

    public String sortBy(final String type, final String direction) {
        final Comparator comparator;
        if ("asc".equals(direction)) {
            comparator = Currency.SortingType.valueOf(type).asc();
        } else {
            comparator = Currency.SortingType.valueOf(type).desc();
        }
        Collections.sort(currencies, comparator);
        return null;
    }
}
