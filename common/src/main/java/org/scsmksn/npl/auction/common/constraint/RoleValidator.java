package org.scsmksn.npl.auction.common.constraint;

import org.scsmksn.npl.auction.enums.RoleEnum;
import org.scsmksn.npl.auction.model.Role;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@Scope("request")
public class RoleValidator extends GenericValidator implements ConstraintValidator<ValidRole, Role> {

    @Override
    public void initialize(ValidRole validRoleConstraint) {
    }

    @Override
    public boolean isValid(final Role role, final ConstraintValidatorContext context) {
        if (role.getId() == null) {
            context.buildConstraintViolationWithTemplate().addConstraintViolation();
            addCustomConstraintViolation(context, "role.not.null.id");
            return false;
        }
        if (RoleEnum.getRoles().stream().noneMatch(roleFromEnum -> roleFromEnum.equals(role))) {
            addCustomConstraintViolation(context, "role.not.exist");
            return false;
        }
        return true;
    }
}
