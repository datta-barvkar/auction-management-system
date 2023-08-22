package org.scsmksn.npl.auction.camel;

import org.apache.camel.builder.RouteBuilder;
import org.scsmksn.npl.auction.logic.adapters.TeamAdapter;
import org.springframework.stereotype.Component;

import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.DELETE_ALL_TEAMS;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.DELETE_TEAM;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.GET_TEAM;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.LIST_TEAMS;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.SAVE_TEAM;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.UPDATE_IMAGE_ID_FOR_TEAM;

@Component
public class TeamRoutesBuilder extends RouteBuilder {

    @Override
    public void configure() {

        configureGetTeamRoute();
        configureSaveTeamRoute();
        configureDeleteTeamRoute();
        configureDeleteAllTeamsRoute();
        configureListTeamsRoute();
        configureUpdateImageIdForTeamRoute();
    }

    private void configureGetTeamRoute() {

        from(GET_TEAM)
                .routeId("get-team")
                .tracing()
                .log("Get Team Route")
                .bean(TeamAdapter.class, "get(${body})")
                .end();
    }

    private void configureSaveTeamRoute() {

        from(SAVE_TEAM)
                .routeId("save-team")
                .tracing()
                .log("Update Team Route")
                .bean(TeamAdapter.class, "save(${body})")
                .end();
    }

    private void configureDeleteTeamRoute() {

        from(DELETE_TEAM)
                .routeId("delete-team")
                .tracing()
                .log("Delete Team Route")
                .bean(TeamAdapter.class, "delete(${body})")
                .end();
    }

    private void configureDeleteAllTeamsRoute() {

        from(DELETE_ALL_TEAMS)
                .routeId("delete-all-teams")
                .tracing()
                .log("Delete All Teams Route")
                .bean(TeamAdapter.class, "deleteAll()")
                .end();
    }

    private void configureListTeamsRoute() {

        from(LIST_TEAMS)
                .routeId("list-teams")
                .tracing()
                .log("List Teams Route")
                .bean(TeamAdapter.class, "list()")
                .end();
    }

    private void configureUpdateImageIdForTeamRoute() {

        from(UPDATE_IMAGE_ID_FOR_TEAM)
                .routeId("update-image-id-for-team")
                .tracing()
                .log("Update Image ID for Team")
                .bean(TeamAdapter.class, "updateImageId(${header.userId}, ${header.imageId})")
                .end();
    }
}
