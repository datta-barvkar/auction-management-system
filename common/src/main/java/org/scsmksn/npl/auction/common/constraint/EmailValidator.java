package org.scsmksn.npl.auction.common.constraint;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Component
@Scope("request")
public class EmailValidator extends GenericValidator implements ConstraintValidator<ValidEmail, String> {

    private static final String EMAIL_REGEXP = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private Pattern emailPattern;

    @Override
    public void initialize(ValidEmail validEmailConstraint) {
        final String emailRegExp = validEmailConstraint.regexp();
        if (emailRegExp.trim().isEmpty()) {
            emailPattern = Pattern.compile(EMAIL_REGEXP);
        } else {
            emailPattern = Pattern.compile(emailRegExp);
        }
    }

    @Override
    public boolean isValid(final String email, final ConstraintValidatorContext context) {
        if (!emailPattern.matcher(email).matches()) {
            addCustomConstraintViolation(context, "user.email.invalid");
            return false;
        }
        return true;
    }
}
