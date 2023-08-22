package org.scsmksn.npl.auction.common.validators;

import org.scsmksn.npl.auction.model.Menu;
import org.scsmksn.npl.auction.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import java.util.List;

@Component
public class MenuValidator extends GenericValidator {

    @Autowired
    private RoleValidator roleValidator;

    @Override
    public boolean supports(final Class<?> clazz) {
        return Menu.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        final Menu menu = (Menu) target;
        if (menu.getName() == null || menu.getName().trim().isEmpty()) {
            errors.rejectValue("name", "menu.name.required", "Name is required!!");
        }
        if (menu.getValue() == null || menu.getValue().trim().isEmpty()) {
            errors.rejectValue("value", "menu.value.required", "Value is required!!");
        }
        if (menu.getUriTemplate() == null || menu.getUriTemplate().trim().isEmpty()) {
            errors.rejectValue("uriTemplate", "menu.uriTemplate.required", "URI Template is required!!");
        }
        final List<Role> roles = menu.getRoles();
        if (menu.getRoles() == null || menu.getRoles().isEmpty()) {
            errors.rejectValue("roles", "menu.roles.required", "At-least 1 Role is required!!");
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
