package JavaEE7.Validation.domain;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by alex on 10/29/15.
 */
public class AddressTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Address address;

    @Before
    public void setUp() {
        address = Address.builder().build();
    }

    @Test
    public void testSetStreetName() throws Exception {
        address.setStreetName("Mystreet");
        address.setStreetName("Mystreet");

    }

    @Test
    public void testSetAppendix() throws Exception {

    }

    @Test
    public void testSetZipCode() throws Exception {

    }

    @Test
    public void testSetCity() throws Exception {

    }

    @Test
    public void testSetNumber() throws Exception {

    }
}