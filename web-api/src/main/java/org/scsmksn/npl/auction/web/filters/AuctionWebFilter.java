package org.scsmksn.npl.auction.web.filters;

import org.scsmksn.npl.auction.common.filters.AbstractAuctionFilter;
import org.scsmksn.npl.auction.common.utils.HttpUtils;
import org.scsmksn.npl.auction.model.Menu;
import org.scsmksn.npl.auction.model.Resource;
import org.scsmksn.npl.auction.model.Role;
import org.scsmksn.npl.auction.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.util.UriTemplate;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.GET_RESOURCE;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.LIST_MENUS_BY_ROLES;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.LIST_ROLES;
import static org.scsmksn.npl.auction.common.utils.HttpUtils.getDefaultMenus;
import static org.scsmksn.npl.auction.common.utils.HttpUtils.getLoggedInUser;
import static org.scsmksn.npl.auction.common.utils.HttpUtils.getRequestParameter;
import static org.scsmksn.npl.auction.common.utils.HttpUtils.getServletPath;
import static org.scsmksn.npl.auction.common.utils.HttpUtils.setDefaultMenus;

@Component
public class AuctionWebFilter extends AbstractAuctionFilter {

    @Value("${spring.application.name:Auction Application}")
    private String appName;

    @Value("${spring.application.logo.name:auction_logo}")
    private String appLogoName;

    @Override
    public void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response
            , final FilterChain filterChain) throws IOException, ServletException {
        HttpUtils.setAppName(appName);
        HttpUtils.setAppLogo(getTemplate().requestWithBody(GET_RESOURCE, appLogoName, Resource.class));
        final User loggedInUser = getLoggedInUser();
        if (loggedInUser != null) {
            final List<Menu> menus;
            if (loggedInUser.getTopMenus().isEmpty()) {
                menus = getTemplate().requestListWithBody(LIST_MENUS_BY_ROLES, loggedInUser.getRoles());
            } else {
                menus = loggedInUser.getTopMenus();
            }
            menus.forEach(this::setSelected);
            loggedInUser.setTopMenus(menus);
        } else {
            List<Menu> defaultMenus = getDefaultMenus();
            if (CollectionUtils.isEmpty(defaultMenus)) {
                final List<Role> roles = getTemplate().requestList(LIST_ROLES);
                defaultMenus = getTemplate().requestListWithBody(LIST_MENUS_BY_ROLES, roles);
            }
            defaultMenus.forEach(this::setSelected);
            setDefaultMenus(defaultMenus);
        }
        filterChain.doFilter(request, response);
    }

    private void setSelected(final Menu menu) {
        menu.setSelected(isSelected(menu.getValue(), menu.getUriTemplate()));
        if (!menu.isSelected()) {
            final List<Menu> subMenus = menu.getSubMenus();
            if (!subMenus.isEmpty()) {
                subMenus.forEach(this::setSelected);
                menu.setSelected(subMenus.stream().anyMatch(Menu::isSelected));
            }
        }
    }

    private boolean isSelected(final String menuName, final String uriTemplate) {
        final UriTemplate template = new UriTemplate(uriTemplate);
        return menuName.equals(getRequestParameter(menuName)) && template.matches(getServletPath());
    }
}
