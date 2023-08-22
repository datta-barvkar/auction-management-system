package org.scsmksn.npl.auction.common.constraint;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Component
@Scope("request")
public class PhoneValidator extends GenericValidator implements ConstraintValidator<ValidPhone, String> {

    private static final String PHONE_REGEXP_1 = "(\\+)[0-9]{2}[0-9]{10}";
    private static final String PHONE_REGEXP_2 = "[0]{1}[0-9]{10}";
    private static final String PHONE_REGEXP_3 = "[0-9]{10}";

    private Pattern emailPattern;

    @Override
    public void initialize(final ValidPhone validPhoneConstraint) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }
}
