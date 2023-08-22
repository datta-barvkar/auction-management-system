package org.scsmksn.npl.auction.camel;

import org.apache.camel.builder.RouteBuilder;
import org.scsmksn.npl.auction.logic.adapters.RoleAdapter;
import org.springframework.stereotype.Component;

import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.LIST_ROLES;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.SAVE_ROLES;

@Component
public class RoleRoutesBuilder extends RouteBuilder {

    @Override
    public void configure() {
        configureListRolesRoute();
        configureSaveRolesRoute();
    }

    private void configureListRolesRoute() {
            from(LIST_ROLES)
                .routeId("list-roles")
                .tracing()
                .log("List Roles Route")
                .bean(RoleAdapter.class, "list(${body})")
                .end();
    }

    private void configureSaveRolesRoute() {
            from(SAVE_ROLES)
                .routeId("save-roles")
                .tracing()
                .log("Save Roles Route")
                .bean(RoleAdapter.class, "saveAll(${body})")
                .end();
    }
}
