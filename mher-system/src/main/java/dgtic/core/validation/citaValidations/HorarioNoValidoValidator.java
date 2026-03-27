package dgtic.core.validation.citaValidations;

import dgtic.core.model.dto.Request.CitasRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class HorarioNoValidoValidator implements ConstraintValidator<HorarioNoValido, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null){
            return false;
        }

        if(value == 15 || value == 30 || value == 60)
        {
           return  true;
        }
        return false;
    }
}

