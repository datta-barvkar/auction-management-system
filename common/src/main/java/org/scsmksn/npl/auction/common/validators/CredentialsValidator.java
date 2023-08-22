package org.scsmksn.npl.auction.common.validators;

import org.scsmksn.npl.auction.model.Credentials;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.regex.Pattern;

import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.USERNAME_EXISTS;

@Component
public class CredentialsValidator extends GenericValidator {

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$");

    @Override
    public boolean supports(final Class<?> clazz) {
        return Credentials.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        final Credentials credentials = (Credentials) target;
        if (credentials.getUsername() == null || credentials.getUsername().trim().isEmpty()) {
            errors.rejectValue("username", "credentials.username.required", "Username is required!!");
        } else {
            if (credentials.getUsername().length() != 8) {
                errors.rejectValue("username", "credentials.username.length", "Username must contain 8 alpha numeric characters!!");
            }
            if (getTemplate().requestWithBody(USERNAME_EXISTS, credentials.getUsername(), Boolean.class)) {
                errors.rejectValue("username", "credentials.username.exists", "Username is already taken, please use another one!!");
            }
        }
        if (credentials.getPassword() == null || credentials.getPassword().trim().isEmpty()) {
            errors.rejectValue("password", "credentials.password.required", "Password is required!!");
        } else {
            if (!PASSWORD_PATTERN.matcher(credentials.getPassword()).matches()) {
                errors.rejectValue("password", "credentials.password.length", "Password is invalid!!");
            }
        }
    }
}
