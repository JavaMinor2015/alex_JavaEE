package JavaEE7.TempConverter.entity;

import javax.ejb.Local;

/**
 * Created by alex on 11/2/15.
 */
@Local
public interface Temperature {

    double convert(final double amount, final String from, final String to);

    String[] getTemps();


}
