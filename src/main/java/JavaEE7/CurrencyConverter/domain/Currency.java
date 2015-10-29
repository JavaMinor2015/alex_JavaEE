package JavaEE7.CurrencyConverter.domain;

import lombok.*;

/**
 * Created by alex on 10/26/15.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Currency {
    private String code;
    private double value;
    private double inverseValue;

    private boolean edited;
    private boolean selected;

    private double valueInDollars;
    private double oneDollarIs;

    public void setValueInDollars(final double value) {
        this.valueInDollars = value;
        this.oneDollarIs = 1 / valueInDollars;
    }

    public void setOneDollarIs(final double value) {
        this.oneDollarIs = value;
        this.valueInDollars = 1 / oneDollarIs;
    }

    public Currency(String code, double dollarValue, double valueInDollars) {
        this.code = code;
        this.valueInDollars = valueInDollars;
        this.oneDollarIs = dollarValue;
    }

    public double calculate(final double amount, final Currency target) {
        return Math.round(amount * (valueInDollars * target.getOneDollarIs()) * 100D) / 100D;
    }

    @Override
    public String toString() {
        return code;
    }

}
