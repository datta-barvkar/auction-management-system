package org.scsmksn.npl.auction.common.validators;

import org.scsmksn.npl.auction.enums.RoleEnum;
import org.scsmksn.npl.auction.model.Team;
import org.scsmksn.npl.auction.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.scsmksn.npl.auction.common.utils.AuctionConstants.ROLE_ID;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.USER_ID;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.USER_EXISTS;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.USER_HAS_ROLE;

@Component
public class TeamValidator extends GenericValidator {

    @Override
    public boolean supports(final Class<?> clazz) {
        return Team.class.equals(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        final Team team = (Team) target;
        if (team.getTeamName() == null || team.getTeamName().trim().isEmpty()) {
            errors.rejectValue("teamName", "team.teamName.required", "Team name is required!!");
        }
        final List<User> owners = team.getOwners();
        final Map<String, Object> headers = new HashMap<>();
        if (owners == null || owners.size() < 1 || owners.size() > 3) {
            errors.rejectValue("owners", "team.owners.required", "Minimum 1 and maximum 3 owners are required.");
        } else {
            headers.put(ROLE_ID, RoleEnum.TEAM_OWNER.getId());
            owners.forEach(user -> {
                if (!getTemplate().requestWithBody(USER_EXISTS, user.getId(), Boolean.class)) {
                    errors.rejectValue("owners", "team.owners.notExist", "User with userId('" + user.getId() + "') does not exist!!");
                }
                headers.put(USER_ID, user.getId());
                if (!getTemplate().requestWithHeaders(USER_HAS_ROLE, headers, Boolean.class)) {
                    errors.rejectValue("owners", "team.owners.notOwner", "User with userId('" + user.getId() + "') is not an owner!!");
                }
            });
        }
        final List<User> admins = team.getAdmins();
        if (admins == null || admins.size() < 1 || admins.size() > 3) {
            errors.rejectValue("admins", "team.admins.required", "Minimum 1 and maximum 3 admins are required.");
        } else {
            headers.put(ROLE_ID, RoleEnum.TEAM_OWNER.getId());
            admins.forEach(user -> {
                if (!getTemplate().requestWithBody(USER_EXISTS, user.getId(), Boolean.class)) {
                    errors.rejectValue("admins", "team.admins.notExist", "User with userId('" + user.getId() + "') does not exist!!");
                }
                headers.put(USER_ID, user.getId());
                if (!getTemplate().requestWithHeaders(USER_HAS_ROLE, headers, Boolean.class)) {
                    errors.rejectValue("admins", "team.admins.notAdmin", "User with userId('" + user.getId() + "') is not a team admin!!");
                }
            });
        }
    }
}
