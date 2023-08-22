package org.scsmksn.npl.auction.common.validators;

import org.scsmksn.npl.auction.model.Profile;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class ProfileValidator extends GenericValidator {

    @Override
    public boolean supports(final Class<?> clazz) {
        return Profile.class.equals(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        final Profile profile = (Profile) target;
        if (profile.getPlayingRole() == null || profile.getPlayingRole().trim().isEmpty()) {
            errors.rejectValue("playingRole", "profile.playingRole.required", "Playing Role is required!!");
        }
        if (profile.getBattingStyle() == null || profile.getBattingStyle().trim().isEmpty()) {
            errors.rejectValue("battingStyle", "profile.battingStyle.required", "Batting Style is required!!");
        }
        if (profile.getBowlingStyle() == null || profile.getBowlingStyle().trim().isEmpty()) {
            errors.rejectValue("bowlingStyle", "profile.bowlingStyle.required", "Bowling Style is required!!");
        }
    }
}
