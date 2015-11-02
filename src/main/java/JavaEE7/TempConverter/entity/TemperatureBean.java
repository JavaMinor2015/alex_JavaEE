package JavaEE7.TempConverter.entity;

import javax.ejb.Stateless;

/**
 * Created by alex on 11/2/15.
 */
@Stateless
public class TemperatureBean implements Temperature {

    public double convert(final double amount, final String from, final String to) {
        return convert(amount, Type.valueOf(from), Type.valueOf(to));
    }

    public double convert(final double amount, final Type from, final Type to) {
        return to.fromCelsius(from.toCelsius(amount));
    }

    @Override
    public String[] getTemps() {
        return new String[]{
                "CELSIUS",
                "FAHRENHEIT",
                "KELVIN"
        };
    }

    public enum Type {
        CELSIUS {
            @Override
            public double toCelsius(final double amount) {
                return amount;
            }

            @Override
            public double fromCelsius(double amount) {
                return amount;
            }
        },
        FAHRENHEIT {
            @Override
            public double toCelsius(double amount) {
                return (amount - 32) / 1.8;
            }

            @Override
            public double fromCelsius(double amount) {
                return (1.8 * amount) + 32;
            }
        },
        KELVIN {
            @Override
            public double toCelsius(double amount) {
                return amount - 273.15;
            }

            @Override
            public double fromCelsius(double amount) {
                return amount + 273.15;
            }
        };

        public abstract double toCelsius(final double from);

        public abstract double fromCelsius(final double to);
    }
}
