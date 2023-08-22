package org.scsmksn.npl.auction.logic.adapters;

import org.scsmksn.npl.auction.logic.services.ApprovalService;
import org.scsmksn.npl.auction.logic.services.TeamService;
import org.scsmksn.npl.auction.model.Approval;
import org.scsmksn.npl.auction.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApprovalAdapter {

    @Autowired
    private ApprovalService approvalService;

    @Autowired
    private TeamService teamService;

    public Approval get(final Long approvalId) {
        return approvalService.findById(approvalId);
    }

    public List<Approval> findAllUnapproved() {
        return approvalService.findAllUnapproved();
    }

    public Approval approve(final Long approvalId, final Long ownerId) {
        final Approval approval = approvalService.approve(approvalId, ownerId);
        final Team team = approval.getTeam();
        team.getPlayers().add(approval.getPlayer());
        teamService.save(team);
        return approval;
    }
}
