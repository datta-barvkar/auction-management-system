package org.scsmksn.npl.auction.camel;

import org.apache.camel.builder.RouteBuilder;
import org.scsmksn.npl.auction.logic.adapters.ResourceAdapter;
import org.springframework.stereotype.Component;

import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.DELETE_RESOURCE;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.GET_RESOURCE;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.SAVE_RESOURCE;

@Component
public class ResourceRoutesBuilder extends RouteBuilder {

    @Override
    public void configure() {

        configureGetResourceRoute();
        configureSaveResourceRoute();
        configureDeleteResourceRoute();
    }

    private void configureGetResourceRoute() {
        from(GET_RESOURCE)
                .routeId("get-resource")
                .tracing()
                .log("Get Auction Resource Route")
                .bean(ResourceAdapter.class, "get(${body})")
                .end();
    }

    private void configureSaveResourceRoute() {
        from(SAVE_RESOURCE)
                .routeId("save-resource")
                .tracing()
                .log("Save Auction Resource Route")
                .bean(ResourceAdapter.class, "save(${body})")
                .end();
    }

    private void configureDeleteResourceRoute() {
        from(DELETE_RESOURCE)
                .routeId("delete-resource")
                .tracing()
                .log("Delete Auction Resource Route")
                .bean(ResourceAdapter.class, "delete(${body})")
                .end();
    }
}
