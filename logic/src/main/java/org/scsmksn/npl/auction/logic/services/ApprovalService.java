package org.scsmksn.npl.auction.logic.services;

import org.scsmksn.npl.auction.model.Approval;

import java.util.List;

public interface ApprovalService {
    Approval findById(Long approvalId);
    List<Approval> findAllUnapproved();
    Approval approve(Long approvalId, Long approvedById);
    List<Approval> saveAll(List<Approval> approvals);
}
