package org.scsmksn.npl.auction.web.filters;

import org.scsmksn.npl.auction.common.acl.WebAuthorizationManager;
import org.scsmksn.npl.auction.common.filters.AbstractAuctionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class WebAuthorizationFilter extends AbstractAuctionFilter {

    @Autowired
    private WebAuthorizationManager authManager;

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response
            , final FilterChain filterChain) throws ServletException, IOException {
        authManager.authorize(request.getMethod(), request.getServletPath());
        filterChain.doFilter(request, response);
    }
}
