package org.scsmksn.npl.auction.logic.adapters;

import org.scsmksn.npl.auction.logic.services.ProfileService;
import org.scsmksn.npl.auction.logic.services.UserService;
import org.scsmksn.npl.auction.model.Profile;
import org.scsmksn.npl.auction.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfileAdapter {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserService userService;

    public Profile get(final Long id) {
        final Profile profile = profileService.get(id);
        final User user = userService.findByCricketProfileId(id);
        profile.setTeams(user.getPlayedForTeams());
        profile.setPlayerId(user.getId());
        profile.setPlayerName(user.getFullName());
        profile.setUserImage(user.getUserImage());
        return profile;
    }

    public Profile save(final Profile profile) {
        return profileService.save(profile);
    }

    public Profile delete(final Long id) {
        return profileService.delete(id);
    }
}
