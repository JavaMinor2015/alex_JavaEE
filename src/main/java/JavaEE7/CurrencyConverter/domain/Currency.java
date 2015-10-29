package JavaEE7.CurrencyConverter.domain;

import JavaEE7.CurrencyConverter.domain.abs.UIEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.util.Comparator;

/**
 * Created by alex on 10/26/15.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Currency extends UIEntity{
    private String code;

    @Min(0)
    private double value;
    @Min(0)
    private double inverseValue;
    @Min(0)
    private double valueInDollars;
    @Min(0)
    private double oneDollarIs;

    public void setValueInDollars(final double value) {
        this.valueInDollars = value;
        this.oneDollarIs = 1 / valueInDollars;
    }

    public void setOneDollarIs(final double value) {
        this.oneDollarIs = value;
        this.valueInDollars = 1 / oneDollarIs;
    }

    // simplified constructor
    public Currency(String code, double dollarValue, double valueInDollars) {
        super(false, false);
        this.code = code;
        this.valueInDollars = valueInDollars;
        this.oneDollarIs = dollarValue;
    }

    public double calculate(final double amount, final Currency target) {
        return Math.round((amount * (valueInDollars * target.getOneDollarIs())) * 100D) / 100D;
    }

    //////////////////////////
    // comparators for sorting
    //

    private static Comparator<Currency> byNameComparator(final String dir) {
        return (c1, c2) -> {
            if ("asc".equals(dir)) {
                return c1.getCode().compareTo(c2.getCode());
            } else {
                return c2.getCode().compareTo(c1.getCode());
            }
        };
    }

    private static Comparator<Currency> byValueComparator(final String dir) {
        return (c1, c2) -> {
            if ("asc".equals(dir)) {
                return Double.valueOf(c1.getValue()).compareTo(c2.getValue());
            } else {
                return Double.valueOf(c2.getValue()).compareTo(c1.getValue());
            }
        };
    }

    private static Comparator<Currency> byInverseValueComparator(final String dir) {
        return (c1, c2) -> {
            if ("asc".equals(dir)) {
                return Double.valueOf(c1.getInverseValue()).compareTo(c2.getInverseValue());
            } else {
                return Double.valueOf(c2.getInverseValue()).compareTo(c1.getInverseValue());
            }
        };
    }

    private static Comparator<Currency> byValueInDollarsComparator(final String dir) {
        return (c1, c2) -> {
            if ("asc".equals(dir)) {
                return Double.valueOf(c1.getValueInDollars()).compareTo(c2.getValueInDollars());
            } else {
                return Double.valueOf(c2.getValueInDollars()).compareTo(c1.getValueInDollars());
            }
        };
    }

    @Override
    public String toString() {
        return code;
    }

    public enum SortingType {
        NAME(byNameComparator("asc"), byNameComparator("desc")),
        VALUE(byValueComparator("asc"), byValueComparator("desc")),
        INVERSE_VALUE(byInverseValueComparator("asc"), byInverseValueComparator("desc")),
        VALUE_IN_DOLLARS(byValueInDollarsComparator("asc"), byValueInDollarsComparator("desc"));

        private Comparator<Currency> compASC;
        private Comparator<Currency> compDESC;

        SortingType(final Comparator<Currency> compASC, final Comparator<Currency> compDESC) {
            this.compASC = compASC;
            this.compDESC = compDESC;
        }

        public Comparator<Currency> asc() {
            return compASC;
        }

        public Comparator<Currency> desc() {
            return compDESC;
        }
    }

}
