package org.scsmksn.npl.auction.common.utils;

import org.scsmksn.npl.auction.model.Menu;
import org.scsmksn.npl.auction.model.Resource;
import org.scsmksn.npl.auction.model.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

import static org.scsmksn.npl.auction.common.utils.AuctionConstants.APP_LOGO;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.APP_NAME;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.DEFAULT_MENUS;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.LOGGED_IN_USER;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.REQUEST_REDIRECT_PATH;
import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;
import static org.springframework.web.context.request.RequestAttributes.SCOPE_SESSION;

public class HttpUtils {

    public static User getLoggedInUser() {
        return (User) getCurrentRequestAttributes().getAttribute(LOGGED_IN_USER, SCOPE_SESSION);
    }

    public static void setLoggedInUser(final User loggedInUser) {
        getCurrentRequestAttributes().setAttribute(LOGGED_IN_USER, loggedInUser, SCOPE_SESSION);
    }

    public static void setRedirectPath(final String servletPath) {
        getCurrentRequestAttributes().setAttribute(REQUEST_REDIRECT_PATH, servletPath, SCOPE_SESSION);
    }

    public static String getRedirectPath() {
        return (String) getCurrentRequestAttributes().getAttribute(REQUEST_REDIRECT_PATH, SCOPE_SESSION);
    }

    public static void removeLoggedInUser() {
        getCurrentRequestAttributes().removeAttribute(LOGGED_IN_USER, SCOPE_SESSION);
    }

    public static void removeRedirectPath() {
        getCurrentRequestAttributes().removeAttribute(REQUEST_REDIRECT_PATH, SCOPE_SESSION);
    }

    public static void setAppName(final String appName) {
        getCurrentRequestAttributes().setAttribute(APP_NAME, appName, SCOPE_REQUEST);
    }

    public static void setAppLogo(final Resource appLogo) {
        getCurrentRequestAttributes().setAttribute(APP_LOGO, appLogo, SCOPE_REQUEST);
    }

    private static ServletRequestAttributes getCurrentRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    }

    public static void setDefaultMenus(final List<Menu> defaultMenus) {
        getCurrentRequestAttributes().setAttribute(DEFAULT_MENUS, defaultMenus, SCOPE_REQUEST);
    }

    @SuppressWarnings("unchecked")
    public static List<Menu> getDefaultMenus() {
        return (List<Menu>) getCurrentRequestAttributes().getAttribute(DEFAULT_MENUS, SCOPE_REQUEST);
    }

    public static String getRequestParameter(final String paramName) {
        return getCurrentRequestAttributes().getRequest().getParameter(paramName);
    }

    public static String getServletPath() {
        return getCurrentRequestAttributes().getRequest().getServletPath();
    }
}
