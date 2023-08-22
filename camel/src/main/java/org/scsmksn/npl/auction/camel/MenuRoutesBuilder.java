package org.scsmksn.npl.auction.camel;

import org.apache.camel.builder.RouteBuilder;
import org.scsmksn.npl.auction.logic.adapters.MenuAdapter;
import org.springframework.stereotype.Component;

import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.LIST_MENUS_BY_ROLES;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.SAVE_MENUS;

@Component
public class MenuRoutesBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        configureListMenusByRolesRoute();
        configureSaveMenusRoute();
    }

    private void configureListMenusByRolesRoute() {
        from(LIST_MENUS_BY_ROLES)
                .routeId("list-menus-by-roles")
                .tracing()
                .log("List Menus by Roles Route")
                .bean(MenuAdapter.class, "listByRoles(${body})")
                .end();
    }

    private void configureSaveMenusRoute() {
        from(SAVE_MENUS)
                .routeId("save-menus")
                .tracing()
                .log("Save Menus")
                .bean(MenuAdapter.class, "saveAll(${body})")
                .end();
    }
}
