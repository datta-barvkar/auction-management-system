package org.scsmksn.npl.auction.common.constraint;

import org.scsmksn.npl.auction.model.Role;
import org.scsmksn.npl.auction.model.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.GET_USER;

@Component
@Scope("request")
public class UserValidator extends GenericValidator implements ConstraintValidator<ValidUser, User> {

    private Role role;

    @Override
    public void initialize(ValidUser validUserConstraint) {
        role = validUserConstraint.role().getRole();
    }

    @Override
    public boolean isValid(final User user, final ConstraintValidatorContext context) {
        if (user.getId() == null) {
            addCustomConstraintViolation(context, "user.not.null.id");
            return false;
        }
        try {
            final User fromDB = getTemplate().requestBody(GET_USER, user.getId(), User.class);
            if (fromDB.getRoles().stream().noneMatch(role -> this.role.equals(role))) {
                addCustomConstraintViolation(context, "user.not." + role.getName().toLowerCase());
                return false;
            }
        } catch (HttpClientErrorException ex) {
            addCustomConstraintViolation(context, "user.not.exist");
            return false;
        }
        return true;
    }
}
