package JavaEE7.TempConverter.logic;

import JavaEE7.TempConverter.entity.Temperature;
import lombok.Getter;
import lombok.Setter;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by alex on 11/2/15.
 */
@Named
@RequestScoped
@Getter
@Setter
public class TempConverter implements Serializable {

    @EJB
    Temperature temperature;

    private double amount;
    private String fromTemp = "CELSIUS";
    private String toTemp = "FAHRENHEIT";

    public double convert() {
        return temperature.convert(amount, fromTemp, toTemp);
    }
}
