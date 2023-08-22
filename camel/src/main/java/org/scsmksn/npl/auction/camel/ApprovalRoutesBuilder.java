package org.scsmksn.npl.auction.camel;

import org.apache.camel.builder.RouteBuilder;
import org.scsmksn.npl.auction.logic.adapters.ApprovalAdapter;
import org.springframework.stereotype.Component;

import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.APPROVE_PLAYER;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.GET_TEAM_APPROVAL;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.LIST_UNAPPROVED;

@Component
public class ApprovalRoutesBuilder extends RouteBuilder {

    @Override
    public void configure() {
        configureGetVerificationRoute();
        configureVerifyPlayerRoute();
        configureListUnapprovedRoute();
    }

    private void configureGetVerificationRoute() {
        from(GET_TEAM_APPROVAL)
                .routeId("get-approval")
                .tracing()
                .log("Get Team Approval Route")
                .bean(ApprovalAdapter.class, "get(${body})")
                .end();
    }

    private void configureVerifyPlayerRoute() {
        from(APPROVE_PLAYER)
                .routeId("approve-player")
                .tracing()
                .log("Approve Player Route")
                .bean(ApprovalAdapter.class, "approve(${header.approvalId}, ${header.ownerId})")
                .end();
    }

    private void configureListUnapprovedRoute() {
        from(LIST_UNAPPROVED)
                .routeId("list-unapproved")
                .tracing()
                .log("List Unapproved Route")
                .bean(ApprovalAdapter.class, "findAllUnapproved()")
                .end();
    }
}
