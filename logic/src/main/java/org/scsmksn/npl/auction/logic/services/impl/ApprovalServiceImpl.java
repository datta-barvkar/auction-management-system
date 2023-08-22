package org.scsmksn.npl.auction.logic.services.impl;

import org.scsmksn.npl.auction.logic.repositories.ApprovalRepository;
import org.scsmksn.npl.auction.logic.services.ApprovalService;
import org.scsmksn.npl.auction.model.Approval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.scsmksn.npl.auction.common.utils.ClientErrorHelper.createNotFound;

@Component
public class ApprovalServiceImpl implements ApprovalService {

    @Autowired
    private ApprovalRepository approvalRepository;

    @Override
    public Approval findById(final Long approvalId) {
        return approvalRepository.findById(approvalId)
                .orElseThrow(() -> createNotFound("Team Approval with id('" + approvalId + "') not found!"));
    }

    @Override
    public List<Approval> findAllUnapproved() {
        return approvalRepository.findAllByNotApproved();
    }

    @Override
    public Approval approve(final Long approvalId, final Long approverId) {
        approvalRepository.updateApprovedAndApproverIdForApproval(approvalId, approverId);
        return approvalRepository.findById(approvalId).orElse(null);
    }

    @Override
    public List<Approval> saveAll(List<Approval> approvals) {
        return approvals.stream()
                .map(approval -> approvalRepository.saveAndFlush(approval)).collect(Collectors.toList());
    }
}
