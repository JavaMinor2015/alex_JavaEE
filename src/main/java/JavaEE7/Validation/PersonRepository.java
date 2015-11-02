package JavaEE7.Validation;

import JavaEE7.Validation.domain.abs.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 10/29/15.
 */
public class PersonRepository {
    private List<Person> persons = new ArrayList<>();

    public void add(final Person person){
        persons.add(person);
    }
}
