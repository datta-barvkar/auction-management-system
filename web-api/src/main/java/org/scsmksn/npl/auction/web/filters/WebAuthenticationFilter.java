package org.scsmksn.npl.auction.web.filters;

import org.scsmksn.npl.auction.common.filters.AbstractAuctionFilter;
import org.scsmksn.npl.auction.model.User;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.scsmksn.npl.auction.common.utils.HttpUtils.getLoggedInUser;
import static org.scsmksn.npl.auction.common.utils.HttpUtils.getServletPath;
import static org.scsmksn.npl.auction.common.utils.HttpUtils.setRedirectPath;

@Component
public class WebAuthenticationFilter extends AbstractAuctionFilter {

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response
            , final FilterChain filterChain) throws IOException {
        final User loggedInUser = getLoggedInUser();
        if (loggedInUser == null) {
            setRedirectPath(getServletPath());
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}
