package org.scsmksn.npl.auction.common.validators;

import org.scsmksn.npl.auction.common.utils.AuctionRoutes;
import org.scsmksn.npl.auction.model.Role;
import org.scsmksn.npl.auction.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import java.util.List;
import java.util.regex.Pattern;

@Component
public class UserValidator extends GenericValidator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("(\\+)[0-9]{2}[0-9]{10}");

    @Autowired
    private CredentialsValidator credentialsValidator;

    @Autowired
    private ProfileValidator profileValidator;

    @Override
    public boolean supports(final Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        final User user = (User) target;
        if (user.getFirstName() == null || user.getFirstName().trim().isEmpty()) {
            errors.rejectValue("firstName", "user.firstname.required", "First name is required!!");
        }
        if (user.getLastName() == null || user.getLastName().trim().isEmpty()) {
            errors.rejectValue("lastName", "user.lastname.required", "Last name is required!!");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            errors.rejectValue("email", "user.email.required", "Email is required!!");
        }
        if (!EMAIL_PATTERN.matcher(user.getEmail()).matches()) {
            errors.rejectValue("email", "user.email.invalid", "Invalid email!!");
        }
        if (user.getPhoneNumber() == null || user.getPhoneNumber().trim().isEmpty()) {
            errors.rejectValue("phoneNumber", "user.phoneNumber.required", "Phone number is required!!");
        }
        if (!PHONE_PATTERN.matcher(user.getPhoneNumber()).matches()) {
            errors.rejectValue("phoneNumber", "user.phoneNumber.invalid", "Invalid phone number!!");
        }
        if (user.getBirthdate() == null) {
            errors.rejectValue("birthdate", "user.birthdate.required", "Birthdate is required!!");
        }
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            errors.rejectValue("roles", "user.roles.required", "At-least 1 role is required!!");
        } else {
            final List<Role> rolesFromDB = getTemplate().requestList(AuctionRoutes.LIST_ROLES);
            if (rolesFromDB.isEmpty()) {
                errors.rejectValue("roles", "user.roles.notExist", "Provided roles don't exist! Please check with administrator and add those!");
            } else {
                errors.pushNestedPath("roles");
                user.getRoles().forEach(role -> {
                    if (rolesFromDB.stream().noneMatch(roleFromDB -> roleFromDB.equals(role))) {
                        errors.reject("user.role.notExist", "Provided Role(id='" + role.getId() + "', name='" + role.getName() +"') doesn't exist!!");
                    }
                });
                errors.popNestedPath();
            }
        }
        if (user.getCredentials() != null && credentialsValidator.supports(user.getCredentials().getClass())) {
            errors.pushNestedPath("credentials");
            ValidationUtils.invokeValidator(credentialsValidator, user.getCredentials(), errors);
            errors.popNestedPath();
        }
        if (user.getProfile() != null && profileValidator.supports(user.getProfile().getClass())) {
            errors.pushNestedPath("profile");
            ValidationUtils.invokeValidator(profileValidator, user.getProfile(), errors);
            errors.popNestedPath();
        }
    }
}
