package org.scsmksn.npl.auction.rest.controllers;

import org.scsmksn.npl.auction.common.controllers.GenericRestController;
import org.scsmksn.npl.auction.common.utils.AuctionConstants;
import org.scsmksn.npl.auction.model.Approval;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.APPROVE_PLAYER;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.LIST_UNAPPROVED;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/api/approvalManagement")
public class ApprovalRestController extends GenericRestController {

    @PostMapping("/approval/approve/{approvalId}")
    public List<Approval> saveApproval(final HttpServletRequest request, @PathVariable final Long approvalId) {
        final Map<String, Object> headers = new HashMap<>();
        headers.put(AuctionConstants.APPROVAL_ID, approvalId);
        headers.put("ownerId", getLoggedInUserId(request));
        getTemplate().sendHeaders(APPROVE_PLAYER, headers);
        return listApprovals();
    }

    @GetMapping("/approval")
    public List<Approval> listApprovals() {
        return getTemplate().requestList(LIST_UNAPPROVED);
    }

    private Long getLoggedInUserId(HttpServletRequest request) {
        final String loggedInUserId = request.getHeader(AuctionConstants.LOGGED_IN_USER);
        if (loggedInUserId == null || loggedInUserId.isEmpty()) {
            throw new HttpClientErrorException("Invalid Header value for 'loggedInUser'!", BAD_REQUEST
                    , BAD_REQUEST.getReasonPhrase(), null, null, Charset.defaultCharset());
        }
        return Long.valueOf(loggedInUserId);
    }
}
