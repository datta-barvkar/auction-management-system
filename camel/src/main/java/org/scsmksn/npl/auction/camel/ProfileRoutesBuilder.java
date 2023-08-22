package org.scsmksn.npl.auction.camel;

import org.apache.camel.builder.RouteBuilder;
import org.scsmksn.npl.auction.logic.adapters.ProfileAdapter;
import org.springframework.stereotype.Component;

import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.DELETE_PROFILE;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.GET_PROFILE;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.SAVE_PROFILE;

@Component
public class ProfileRoutesBuilder extends RouteBuilder {

    @Override
    public void configure() {
        configureGetProfileRoute();
        configureSaveProfileRoute();
        configureDeleteProfileRoute();
    }

    private void configureGetProfileRoute() {
        from(GET_PROFILE)
                .routeId("get-profile")
                .tracing()
                .log("Get Cricket Profile Route")
                .bean(ProfileAdapter.class, "get(${body})")
                .end();
    }

    private void configureSaveProfileRoute() {
        from(SAVE_PROFILE)
                .routeId("save-profile")
                .tracing()
                .log("Save Cricket Profile Route")
                .bean(ProfileAdapter.class, "save(${body})")
                .end();
    }

    private void configureDeleteProfileRoute() {
        from(DELETE_PROFILE)
                .routeId("delete-profile")
                .tracing()
                .log("Delete Cricket Profile Route")
                .bean(ProfileAdapter.class, "delete(${body})")
                .end();
    }
}
