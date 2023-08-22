package org.scsmksn.npl.auction.common.validators;

import org.scsmksn.npl.auction.model.AccessControl;
import org.scsmksn.npl.auction.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class AccessControlValidator implements Validator {

    @Autowired
    private RoleValidator roleValidator;

    @Override
    public boolean supports(final Class<?> clazz) {
        return AccessControl.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        final AccessControl accessControl = (AccessControl) target;
        if (accessControl.getName() == null || accessControl.getName().trim().isEmpty()) {
            errors.rejectValue("name", "accessControl.name.required", "Name is required!!");
        }
        if (accessControl.getOperation() == null || accessControl.getOperation().trim().isEmpty()) {
            errors.rejectValue("operation", "accessControl.operation.required", "Operation is required!!");
        }
        if (accessControl.getGroup() == null || accessControl.getGroup().trim().isEmpty()) {
            errors.rejectValue("group", "accessControl.group.required", "Group is required!!");
        }
        if (accessControl.getPathPattern() == null || accessControl.getPathPattern().trim().isEmpty()) {
            errors.rejectValue("pathPattern", "accessControl.pathPattern.required", "Path Pattern is required!!");
        }
        final List<Role> roles = accessControl.getRoles();
        if (accessControl.getRoles() == null || accessControl.getRoles().isEmpty()) {
            errors.rejectValue("roles", "accessControl.roles.required", "At-least 1 Role is required!!");
        } else {
            if (roleValidator.supports(Role.class)) {
                roles.forEach(role -> {
                    errors.pushNestedPath("roles");
                    ValidationUtils.invokeValidator(roleValidator, role, errors);
                    errors.popNestedPath();
                });
            }
        }
    }
}
