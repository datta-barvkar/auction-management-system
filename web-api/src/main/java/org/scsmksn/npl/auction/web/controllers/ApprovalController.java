package org.scsmksn.npl.auction.web.controllers;

import org.scsmksn.npl.auction.common.controllers.GenericWebController;
import org.scsmksn.npl.auction.model.Approval;
import org.scsmksn.npl.auction.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.scsmksn.npl.auction.common.utils.AuctionConstants.APPROVAL_ID;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.OWNER_ID;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.TEAM_APPROVALS;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.APPROVE_PLAYER;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.LIST_UNAPPROVED;
import static org.scsmksn.npl.auction.common.utils.HttpUtils.getLoggedInUser;

@Controller
@RequestMapping("/approval-management/approval")
public class ApprovalController extends GenericWebController {

    @PutMapping("/{approvalId}")
    public String approve(@PathVariable final Long approvalId) {
        final User loggedInUser = getLoggedInUser();
        final Map<String, Object> headers = new HashMap<>();
        headers.put(APPROVAL_ID, approvalId);
        headers.put(OWNER_ID, loggedInUser.getId());
        getTemplate().sendHeaders(APPROVE_PLAYER, headers);
        return "redirect:/approval-management/approval";
    }

    @GetMapping()
    public String listApprovals(final Model model) {
        final List<Approval> approvals = getTemplate().requestList(LIST_UNAPPROVED);
        model.addAttribute(TEAM_APPROVALS, approvals.stream()
                .peek(approval -> approval.setEditable(getAuthManager().isAdmin()
                        || getAuthManager().isRealOwnerForApproval(String.valueOf(approval.getId())))
                ).collect(Collectors.toList()));
        return "approval-list";
    }
}
