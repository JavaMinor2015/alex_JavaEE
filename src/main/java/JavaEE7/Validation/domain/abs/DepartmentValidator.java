package JavaEE7.Validation.domain.abs;

/**
 * Created by alex on 10/29/15.
 */

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class DepartmentValidator implements ConstraintValidator<ValidDepartment, String> {

    List<String> validDepartments;

    @Override
    public void initialize(ValidDepartment constraintAnnotation) {
        validDepartments = new ArrayList<>();
        validDepartments.add("Finance");
        validDepartments.add("HR");
    }

    @Override
    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
        if(validDepartments.contains(object)){
            return true;
        }
        return false;
    }

}