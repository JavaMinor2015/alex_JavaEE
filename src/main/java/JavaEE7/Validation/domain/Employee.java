package JavaEE7.Validation.domain;

import JavaEE7.Validation.domain.abs.Person;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Created by alex on 10/29/15.
 */
@Named
@SessionScoped
public class Employee extends Person {

    @Override
    public String getSomethingValid(){
        return "OMG THIS IS NOT VALID AT ALL";
    }
}
