package org.scsmksn.npl.auction.config;

import org.scsmksn.npl.auction.web.filters.AuctionWebFilter;
import org.scsmksn.npl.auction.web.filters.WebAuthenticationFilter;
import org.scsmksn.npl.auction.web.filters.WebAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuctionWebFilterConfiguration {

    public static final String[] AUTH_EXCLUDE_PATTERNS = {"/h2-console", "/", "/home", "/register", "/login", "/api"};

    @Autowired
    private AuctionWebFilter auctionWebFilter;

    @Autowired
    private WebAuthenticationFilter webAuthenticationFilter;

    @Autowired
    private WebAuthorizationFilter webAuthorizationFilter;

    @Bean
    public FilterRegistrationBean<AuctionWebFilter> auctionWebFilterBean(){
        auctionWebFilter.setExcludedPathPatterns("/h2-console", "/api");
        final FilterRegistrationBean<AuctionWebFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(auctionWebFilter);
        registrationBean.setName("auction-web-filter");
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<WebAuthenticationFilter> webAuthenticationFilterBean(){
        webAuthenticationFilter.setExcludedPathPatterns(AUTH_EXCLUDE_PATTERNS);
        final FilterRegistrationBean<WebAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(webAuthenticationFilter);
        registrationBean.setName("web-authentication-filter");
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(2);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<WebAuthorizationFilter> webAuthorizationFilterBean(){
        webAuthorizationFilter.setExcludedPathPatterns(AUTH_EXCLUDE_PATTERNS);
        final FilterRegistrationBean<WebAuthorizationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(webAuthorizationFilter);
        registrationBean.setName("web-authorization-filter");
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(3);
        return registrationBean;
    }
}
