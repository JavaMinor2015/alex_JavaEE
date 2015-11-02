package JavaEE7.TempConverter.entity;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by alex on 11/2/15.
 */
public class TemperatureBeanTest {

    @Test
    public void testConvert() throws Exception {
        TemperatureBean bean = new TemperatureBean();


        assertThat(TemperatureBean.Type.FAHRENHEIT.toCelsius(5), is(-15.0));

        assertThat(bean.convert(5, "CELSIUS", "CELSIUS"), is(5.0));
        assertThat(bean.convert(5, "CELSIUS", "KELVIN"), is(278.15));
        assertThat(bean.convert(278.15, "KELVIN", "CELSIUS"), is(5.0));
        assertThat(bean.convert(5, "KELVIN", "KELVIN"), is(5.0));
        assertThat(bean.convert(5, "FAHRENHEIT", "FAHRENHEIT"), is(5.0));
        assertThat(bean.convert(32, "CELSIUS", "FAHRENHEIT"), is(89.6));
    }



}