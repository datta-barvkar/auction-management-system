package org.scsmksn.npl.auction.common.controllers;

import org.scsmksn.npl.auction.common.acl.WebAuthorizationManager;
import org.scsmksn.npl.auction.common.template.WebCamelRequestTemplate;
import org.scsmksn.npl.auction.enums.BattingStyle;
import org.scsmksn.npl.auction.enums.BowlingStyle;
import org.scsmksn.npl.auction.enums.PlayingRole;
import org.scsmksn.npl.auction.model.Resource;
import org.scsmksn.npl.auction.model.Role;
import org.scsmksn.npl.auction.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.scsmksn.npl.auction.common.utils.AuctionConstants.BATTING_STYLES;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.BOWLING_STYLES;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.GENDERS;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.IMAGE;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.MARITAL_STATUSES;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.PLAYING_ROLES;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.ROLES;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.USER;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.LIST_ROLES;

public class GenericWebController {

    @Autowired
    private WebCamelRequestTemplate template;

    @Autowired
    private WebAuthorizationManager authManager;

    public WebCamelRequestTemplate getTemplate() {
        return template;
    }

    protected WebAuthorizationManager getAuthManager() {
        return authManager;
    }

    protected void loadModelWithAttributes(final Model model) {
        model.addAttribute(USER, new User());
        if (authManager.isAdmin()) {
            final List<Role> roles = template.requestList(LIST_ROLES);
            model.addAttribute(ROLES, roles);
        }
        model.addAttribute(GENDERS, Arrays.asList("Male", "Female", "Other"));
        model.addAttribute(MARITAL_STATUSES, Arrays.asList("Married", "Unmarried"));
        model.addAttribute(BATTING_STYLES, BattingStyle.getBattingStyles());
        model.addAttribute(BOWLING_STYLES, BowlingStyle.getBowlingStyles());
        model.addAttribute(PLAYING_ROLES, PlayingRole.getPlayingRoles());
    }

    protected ServletRequestAttributes getServletRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    }

    protected Resource geUpdatedImage(final String imageName, final MultipartFile imageFile, final Resource userImage)
            throws IOException {
        userImage.setName(imageName);
        userImage.setType(IMAGE);
        final byte[] bytes = imageFile.getBytes();
        userImage.setFile(bytes);
        userImage.setBase64File(Base64.getEncoder().encodeToString(bytes));
        return userImage;
    }

    protected void setCanUpdateAndDelete(final User user) {
        final boolean isAdmin = getAuthManager().isAdmin();
        user.setEditable(isAdmin || getAuthManager().isSameUser(String.valueOf(user.getId())));
        user.setDeletable(isAdmin);
    }

    protected Map<String, Object> getUserModelMap(final User user) {
        final Map<String, Object> userModelMap = new HashMap<>();
        userModelMap.put(USER, user);
        if (getAuthManager().isAdmin()) {
            final List<Role> roles = getTemplate().requestList(LIST_ROLES);
            userModelMap.put(ROLES, roles);
        }
        userModelMap.put(GENDERS, Arrays.asList("Male", "Female", "Other"));
        userModelMap.put(MARITAL_STATUSES, Arrays.asList("Married", "Unmarried"));
        return userModelMap;
    }
}
