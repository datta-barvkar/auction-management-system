package org.scsmksn.npl.auction.common.validators;

import org.scsmksn.npl.auction.enums.RoleEnum;
import org.scsmksn.npl.auction.model.Role;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class RoleValidator extends GenericValidator {

    @Override
    public boolean supports(final Class<?> clazz) {
        return Role.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        final Role role = (Role) target;
        if (RoleEnum.getRoles().stream().noneMatch(enumRole -> enumRole.equals(role))) {
            errors.reject("role.incorrectRole", "Incorrect role provided!!");
        }
    }
}
