package JavaEE7.Validation.domain.abs;

import JavaEE7.Validation.domain.Address;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.validation.ConstraintViolationException;

/**
 * Created by alex on 10/29/15.
 */
public class PersonTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Person validPerson;

    @Before
    public void setUp() {
        validPerson = Person.builder()
                .name("Jack")
                .department("finance")
                .address(
                        Address.builder().zipCode("1010AB")
                                .city("Dordrecht")
                                .number(5)
                                .appendix("A")
                                .streetName("Laanstraat")
                                .build()
                ).build();
    }

    @Test
    public void testNotNulls() {
        thrown.expect(ConstraintViolationException.class);
        Person p = new Person(null, null, null);
    }

}