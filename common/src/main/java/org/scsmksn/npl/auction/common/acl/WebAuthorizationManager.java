package org.scsmksn.npl.auction.common.acl;

import groovy.lang.GroovyShell;
import org.scsmksn.npl.auction.common.template.WebCamelRequestTemplate;
import org.scsmksn.npl.auction.common.utils.ClientErrorHelper;
import org.scsmksn.npl.auction.enums.RoleEnum;
import org.scsmksn.npl.auction.model.AccessControl;
import org.scsmksn.npl.auction.model.Approval;
import org.scsmksn.npl.auction.model.Params;
import org.scsmksn.npl.auction.model.Role;
import org.scsmksn.npl.auction.model.Team;
import org.scsmksn.npl.auction.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.GET_ACL_BY_OPERATION_AND_PATH;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.GET_TEAM;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.GET_TEAM_APPROVAL;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.LIST_USERS;
import static org.scsmksn.npl.auction.common.utils.HttpUtils.getLoggedInUser;

@Component
public class WebAuthorizationManager {

    public static final String AUTH_MANAGER = "authManager";
    public static final String OPERATION = "operation";
    public static final String PATH = "path";

    @Autowired
    private WebCamelRequestTemplate template;

    private final GroovyShell shell = new GroovyShell();

    public void authorize(final String method, final String servletPath) {
        final User loggedInUser = getLoggedInUser();
        final Params params = Params.getParamsBuilder().putJpaQueryParam(OPERATION, method)
                .putJpaQueryParam(PATH, servletPath).build();
        final AccessControl auth = template.requestWithHeaders(GET_ACL_BY_OPERATION_AND_PATH, params, AccessControl.class);
        if (!authorizedWithRoles(auth.getRoles(), loggedInUser.getRoles())) {
            throw ClientErrorHelper.createUnauthorized(auth.getFailureMessage());
        }
        if (auth.getExpression() != null) {
            final UriTemplate template = new UriTemplate(auth.getPathPattern());
            final Map<String, String> pathParams = template.match(servletPath);
            final boolean result = evaluateExpression(auth.getExpression(), pathParams);
            if (!result) {
                throw ClientErrorHelper.createUnauthorized(auth.getFailureMessage());
            }
        }
    }

    public boolean isAdmin() {
        final User loggedInUser = getLoggedInUser();
        return loggedInUser.getRoles().stream().anyMatch(role -> role.equals(RoleEnum.ADMIN.getRole()));
    }

    public boolean isRealOwnerForApproval(final String approvalId) {
        final Approval approval = template.requestWithBody(GET_TEAM_APPROVAL, Long.valueOf(approvalId)
                , Approval.class);
        final Team team = approval.getTeam();
        return isRealOwner(team);
    }

    public boolean isRealOwnerOrTeamAdmin(final String teamId) {
        final Team team = template.requestWithBody(GET_TEAM, Long.valueOf(teamId), Team.class);
        final User loggedInUser = getLoggedInUser();
        return isRealOwner(team) || team.getAdmins().stream().anyMatch(admin -> admin.equals(loggedInUser));
    }

    public boolean isSameUser(final String userId) {
        return Long.valueOf(userId).equals(getLoggedInUser().getId());
    }

    public boolean notLastAdmin() {
        final List<User> users = template.requestList(LIST_USERS);
        final List<User> admins = users.stream()
                .filter(user -> user.getRoles().stream()
                        .anyMatch(role -> role.equals(RoleEnum.ADMIN.getRole())))
                .collect(Collectors.toList());
        return admins.size() > 1;
    }

    private boolean isRealOwner(Team team) {
        final User loggedInUser = getLoggedInUser();
        return team.getOwners().stream().anyMatch(owner -> owner.equals(loggedInUser));
    }

    private boolean authorizedWithRoles(final List<Role> authorizedRoles, final List<Role> userRoles) {
        return authorizedRoles.stream().anyMatch(authorizedRole -> userRoles.stream()
                .anyMatch(userRole -> userRole.equals(authorizedRole)));
    }

    private boolean evaluateExpression(final String expression, final Map<String, String> variables) {
        shell.setProperty(AUTH_MANAGER, this);
        variables.forEach(shell::setVariable);
        return (boolean) shell.evaluate(expression);
    }
}
