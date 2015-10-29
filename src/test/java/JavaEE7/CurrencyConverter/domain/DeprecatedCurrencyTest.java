package JavaEE7.CurrencyConverter.domain;

import junit.framework.TestCase;

/**
 * Created by alex on 10/26/15.
 */
public class DeprecatedCurrencyTest extends TestCase {

    public void testCalculate() throws Exception {

        Currency euro = new Currency("EUR", 0.88, 1.14);
        Currency dollar = new Currency("DOL", 1, 1);

        // 5.68 dollar is 5 euro
        assertEquals(dollar.calculate(5.68, euro), 5, 0.1);

        // 5 euro is 5.68 dollar
        assertEquals(euro.calculate(5, dollar), 5.68, 0.1);

        // 10 dollar is 8.8 euro
        assertEquals(dollar.calculate(10, euro), 8.8, 0.1);

        // 10 dollar is 10 dollar
        assertEquals(dollar.calculate(10, dollar), 10, 0.1);
    }
}