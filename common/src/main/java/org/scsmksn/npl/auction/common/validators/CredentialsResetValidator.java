package org.scsmksn.npl.auction.common.validators;

import org.scsmksn.npl.auction.common.utils.HttpUtils;
import org.scsmksn.npl.auction.model.Credentials;
import org.scsmksn.npl.auction.model.CredentialsReset;
import org.scsmksn.npl.auction.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class CredentialsResetValidator implements Validator {

    @Override
    public boolean supports(final Class<?> clazz) {
        return CredentialsReset.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        final User loggedInUser = HttpUtils.getLoggedInUser();
        final Credentials credentials = loggedInUser.getCredentials();
        final CredentialsReset credentialsReset = (CredentialsReset) target;
        if (!credentials.getUsername().equals(credentialsReset.getUsername())) {
            errors.rejectValue("username", "credentials.username", "Username is incorrect!!");
        }
        if (!credentials.getPassword().equals(credentialsReset.getOldPassword())) {
            errors.rejectValue("oldPassword", "credentials.oldPassword", "Old password is incorrect!!");
        }
        if (!credentialsReset.getNewPassword().equals(credentialsReset.getRepeatPassword())) {
            errors.reject("credentials.passwordsMisMatch", "Passwords not matched!!");
        } else {
            final List<String> oldPasswords = credentials.getOldPasswords();
            if (oldPasswords.contains(credentialsReset.getNewPassword())) {
                errors.rejectValue("newPassword", "credentials.newPassword", "Password should be different from last 3 old passwords!!");
            }
        }
    }
}
