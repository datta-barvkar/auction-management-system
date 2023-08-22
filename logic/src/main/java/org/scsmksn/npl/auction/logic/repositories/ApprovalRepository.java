package org.scsmksn.npl.auction.logic.repositories;

import org.scsmksn.npl.auction.model.Approval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.scsmksn.npl.auction.common.utils.AuctionConstants.APPROVAL_ID;

@Repository
public interface ApprovalRepository extends JpaRepository<Approval, Long> {

    @Query(
        value = "SELECT * FROM APPROVALS WHERE NOT APPROVED",
        nativeQuery = true
    )
    List<Approval> findAllByNotApproved();

    @Query(
        value = "UPDATE APPROVALS SET APPROVED = TRUE, APPROVER_ID = :approverId WHERE id = :approvalId",
        nativeQuery = true
    )
    void updateApprovedAndApproverIdForApproval(@Param(APPROVAL_ID) Long approvalId
            , @Param("approverId") Long approverId);
}
