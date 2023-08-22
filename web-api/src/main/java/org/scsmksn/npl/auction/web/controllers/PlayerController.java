package org.scsmksn.npl.auction.web.controllers;

import org.scsmksn.npl.auction.common.controllers.GenericWebController;
import org.scsmksn.npl.auction.model.Profile;
import org.scsmksn.npl.auction.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

import static org.scsmksn.npl.auction.common.utils.AuctionConstants.PAGE_TITLE;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.PLAYER_ID;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.PROFILE;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.PROFILE_ID;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.DELETE_PROFILE;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.GET_PROFILE;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.SAVE_PROFILE;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.UPDATE_PROFILE_ID_FOR_USER;
import static org.scsmksn.npl.auction.common.utils.HttpUtils.getLoggedInUser;
import static org.scsmksn.npl.auction.common.utils.PageTitles.ADD_PROFILE;
import static org.scsmksn.npl.auction.common.utils.PageTitles.EDIT_PROFILE;
import static org.scsmksn.npl.auction.common.utils.PageTitles.VIEW_PROFILE;

@Controller
@RequestMapping("/player-management/profile")
public class PlayerController extends GenericWebController {

    @GetMapping(path = "/{profileId}", params = "view")
    public String viewProfile(@PathVariable(PROFILE_ID) final Long profileId, final Model model) {
        final Profile fromDB = getTemplate().requestWithBody(GET_PROFILE, profileId, Profile.class);
        final String playerId = String.valueOf(fromDB.getUser().getId());
        fromDB.setDeletable(getAuthManager().isSameUser(playerId));
        fromDB.setEditable(getAuthManager().isSameUser(playerId));
        model.addAttribute(PROFILE, fromDB);
        model.addAttribute(PAGE_TITLE, VIEW_PROFILE);
        return "profile-view";
    }

    @GetMapping(path = "/{profileId}", params = "edit")
    public String updateProfile(@PathVariable(PROFILE_ID) final Long profileId, final Model model) {
        final Profile fromDB = getTemplate().requestWithBody(GET_PROFILE, profileId, Profile.class);
        model.addAttribute(PROFILE, fromDB);
        model.addAttribute(PAGE_TITLE, EDIT_PROFILE);
        return "profile-update";
    }

    @PutMapping("/{profileId}")
    public String updateProfile(@PathVariable(PROFILE_ID) final Long profileId
            , @ModelAttribute(PROFILE) final Profile profile) {
        getTemplate().sendBody(SAVE_PROFILE, profile);
        return "redirect:/player-management/profile/" + profileId;
    }

    @DeleteMapping("/{profileId}")
    public String deleteProfile(@PathVariable(PROFILE_ID) final Long profileId) {
        final Profile fromDB = getTemplate().requestWithBody(DELETE_PROFILE, profileId, Profile.class);
        final Long playerId = fromDB.getPlayerId();
        getTemplate().sendHeader(UPDATE_PROFILE_ID_FOR_USER, PLAYER_ID, fromDB.getPlayerId());
        return "redirect:/user-management/user/" + playerId;
    }

    @GetMapping()
    public String createProfile(final Model model) {
        final User player = getLoggedInUser();
        final Profile profile = new Profile();
        profile.setPlayerName(player.getFullName());
        profile.setPlayerId(player.getId());
        profile.setTeams(player.getPlayedForTeams());
        profile.setUserImage(player.getUserImage());
        model.addAttribute(PAGE_TITLE, ADD_PROFILE);
        return "profile-update";
    }

    @PostMapping()
    public String createProfile(@ModelAttribute(PROFILE) final Profile profile) {
        final Profile fromDB = getTemplate().requestWithBody(SAVE_PROFILE, profile, Profile.class);
        final HashMap<String, Object> headers = new HashMap<>();
        headers.put(PLAYER_ID, getLoggedInUser().getId());
        headers.put(PROFILE_ID, fromDB.getId());
        getTemplate().sendHeaders(UPDATE_PROFILE_ID_FOR_USER, headers);
        return "redirect:/player-management/profile/" + fromDB.getId();
    }
}
