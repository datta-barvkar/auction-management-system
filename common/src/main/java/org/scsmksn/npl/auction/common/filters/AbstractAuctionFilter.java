package org.scsmksn.npl.auction.common.filters;

import org.scsmksn.npl.auction.common.template.WebCamelRequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractAuctionFilter extends OncePerRequestFilter {

    private List<String> excludedPathPatterns;

    @Autowired
    private WebCamelRequestTemplate template;

    public WebCamelRequestTemplate getTemplate() {
        return template;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return excludedPathPatterns.stream().anyMatch(pathPattern -> request.getServletPath().equals(pathPattern)
                || request.getServletPath().startsWith(pathPattern));
    }

    public void setExcludedPathPatterns(final List<String> excludedPathPatterns) {
        this.excludedPathPatterns = excludedPathPatterns;
    }

    public void setExcludedPathPatterns(final String... excludedPathPatterns) {
        if (this.excludedPathPatterns == null) {
            this.excludedPathPatterns = new ArrayList<>();
        }
        this.excludedPathPatterns.addAll(Arrays.asList(excludedPathPatterns));
    }
}
