package org.scsmksn.npl.auction.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum PlayingRole {

    BATTER("Batter"), BOWLER("Bowler"), ALL_ROUNDER("All Rounder"), WICKET_KEEPER("Wicket Keeper");

    private final String playingRole;

    PlayingRole(final String playingRole) {
        this.playingRole = playingRole;
    }

    public String getPlayingRole() {
        return playingRole;
    }

    public static List<String> getPlayingRoles() {
        final PlayingRole[] playingRoles = PlayingRole.values();
        return Arrays.stream(playingRoles).map(PlayingRole::getPlayingRole).collect(Collectors.toList());
    }
}
