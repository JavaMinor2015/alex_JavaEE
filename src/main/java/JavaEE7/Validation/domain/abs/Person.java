package JavaEE7.Validation.domain.abs;

import JavaEE7.Validation.domain.Address;
import lombok.*;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by alex on 10/29/15.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Named
@SessionScoped
public class Person implements Serializable{

    @NotNull(message = "Name may not be null")
    private String name;

    @ValidDepartment
    @NotNull(message = "Department may not be null")
    private String department;

    @NotNull(message = "Address may not be null")
    private Address address = new Address();

    // only Finance and HR are valid responses
    @ValidDepartment
    public String getSomethingValid(){
        return "Finance";
    }
    // only Finance and HR are valid responses
    @ValidDepartment
    public String getSomethingInvalid(){
        return "This is not valid";
    }

}
