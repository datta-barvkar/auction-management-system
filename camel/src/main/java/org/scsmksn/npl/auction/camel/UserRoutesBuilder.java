package org.scsmksn.npl.auction.camel;

import org.apache.camel.builder.RouteBuilder;
import org.scsmksn.npl.auction.logic.adapters.UserAdapter;
import org.springframework.stereotype.Component;

import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.DELETE_ALL_USERS;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.DELETE_USER;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.GET_USER;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.LIST_ADMINS;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.LIST_AUCTIONEERS;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.LIST_AUCTION_ADMINS;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.LIST_PLAYERS;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.LIST_STATS_ADMINS;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.LIST_TEAM_ADMINS;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.LIST_TEAM_OWNERS;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.LIST_USERS;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.SAVE_USER;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.UPDATE_IMAGE_ID_FOR_USER;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.UPDATE_PROFILE_ID_FOR_USER;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.USERNAME_EXISTS;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.USER_EXISTS;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.USER_HAS_ROLE;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.VALIDATE_USER;

@Component
public class UserRoutesBuilder extends RouteBuilder {

    @Override
    public void configure() {

        configureGetUserRoute();
        configureUpdateUserRoute();
        configureDeleteUserRoute();
        configureDeleteAllUsersRoute();
        configureListUsersRoute();
        configureListAdminsRoute();
        configureListAuctionAdminsRoute();
        configureListAuctioneersRoute();
        configureListTeamOwnersRoute();
        configureListTeamAdminsRoute();
        configureListStatsAdminsRoute();
        configureListPlayersRoute();
        configureValidateLoginRoute();
        configureUpdateProfileIdForUserRoute();
        configureUpdateImageIdForUserRoute();
        configureUsernameExistsRoute();
        configureUserExistsRoute();
        configureUserHasRoleRoute();
    }

    private void configureGetUserRoute() {

        from(GET_USER)
                .routeId("get-user")
                .tracing()
                .log("Get User Route")
                .bean(UserAdapter.class, "find(${body})")
                .end();
    }

    private void configureUpdateUserRoute() {

        from(SAVE_USER)
                .routeId("save-user")
                .tracing()
                .log("Update User Route")
                .bean(UserAdapter.class, "save(${body})")
                .end();
    }

    private void configureDeleteUserRoute() {

        from(DELETE_USER)
                .routeId("delete-user")
                .tracing()
                .log("Delete User Route")
                .bean(UserAdapter.class, "delete(${body})")
                .end();
    }

    private void configureDeleteAllUsersRoute() {

        from(DELETE_ALL_USERS)
                .routeId("delete-all-users")
                .tracing()
                .log("Delete All Users Route")
                .bean(UserAdapter.class, "deleteAll()")
                .end();
    }

    private void configureListUsersRoute() {

        from(LIST_USERS)
                .routeId("list-users")
                .tracing()
                .log("List Users Route")
                .bean(UserAdapter.class, "findAll()")
                .end();
    }

    private void configureListAdminsRoute() {

        from(LIST_ADMINS)
                .routeId("list-admins")
                .tracing()
                .log("List Admins Route")
                .bean(UserAdapter.class, "findAllAdmins()")
                .end();
    }

    private void configureListAuctionAdminsRoute() {

        from(LIST_AUCTION_ADMINS)
                .routeId("list-auction-admins")
                .tracing()
                .log("List Auction Admins Route")
                .bean(UserAdapter.class, "findAllAuctionAdmins()")
                .end();
    }

    private void configureListAuctioneersRoute() {

        from(LIST_AUCTIONEERS)
                .routeId("list-auctioneers")
                .tracing()
                .log("List Auctioneers Route")
                .bean(UserAdapter.class, "findAllAuctioneers()")
                .end();
    }

    private void configureListTeamOwnersRoute() {

        from(LIST_TEAM_OWNERS)
                .routeId("list-team-owners")
                .tracing()
                .log("List Team Owners Route")
                .bean(UserAdapter.class, "findAllTeamOwners()")
                .end();
    }

    private void configureListTeamAdminsRoute() {

        from(LIST_TEAM_ADMINS)
                .routeId("list-team-admins")
                .tracing()
                .log("List Team Admins Route")
                .bean(UserAdapter.class, "findAllTeamAdmins()")
                .end();
    }

    private void configureListStatsAdminsRoute() {

        from(LIST_STATS_ADMINS)
                .routeId("list-stats-admins")
                .tracing()
                .log("List Stats Admins Route")
                .bean(UserAdapter.class, "findAllStatsAdmins()")
                .end();
    }

    private void configureListPlayersRoute() {

        from(LIST_PLAYERS)
                .routeId("list-players")
                .tracing()
                .log("List Players Route")
                .bean(UserAdapter.class, "findAllPlayers()")
                .end();
    }

    private void configureValidateLoginRoute() {

        from(VALIDATE_USER)
                .routeId("validate-user")
                .tracing()
                .log("Validate User Route")
                .bean(UserAdapter.class, "validateLogin(${body})")
                .end();
    }

    private void configureUpdateProfileIdForUserRoute() {

        from(UPDATE_PROFILE_ID_FOR_USER)
                .routeId("update-profile-id-for-user")
                .tracing()
                .log("Update Cricket Profile Id for User Route")
                .bean(UserAdapter.class, "updateProfileId(${header.playerId}, ${header.cricketProfileId})")
                .end();
    }

    private void configureUpdateImageIdForUserRoute() {

        from(UPDATE_IMAGE_ID_FOR_USER)
                .routeId("update-image-id-for-user")
                .tracing()
                .log("Update Image Id for User Route")
                .bean(UserAdapter.class, "updateImageId(${header.playerId}, ${header.imageId})")
                .end();
    }

    private void configureUsernameExistsRoute() {
        from(USERNAME_EXISTS)
                .routeId("username-exists")
                .tracing()
                .log("Username Exists Route")
                .bean(UserAdapter.class, "usernameExists(${body})")
                .end();
    }

    private void configureUserExistsRoute() {
        from(USER_EXISTS)
                .routeId("user-exists")
                .tracing()
                .log("User Exists Route")
                .bean(UserAdapter.class, "userExists(${body})")
                .end();
    }

    private void configureUserHasRoleRoute() {
        from(USER_HAS_ROLE)
                .routeId("user-has-role")
                .tracing()
                .log("User Has Role Route")
                .bean(UserAdapter.class, "userHasRole(${header.userId}, ${header.roleId})")
                .end();
    }
}
