package org.scsmksn.npl.auction.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum BowlingStyle {

    LAF("Left Arm Fast"),
    LAM("Left Arm Medium"),
    RAF("Right Arm Fast"),
    RAM("Right Arm Medium"),
    SOS("Slow Off Spinner"),
    SLS("Slow Leg Spinner"),
    SLAO("Slow Left Arm Orthodox"),
    SLAW("Slow Left Arm Wrist Spin");

    private final String bowlingStyle;

    public String getBowlingStyle() {
        return bowlingStyle;
    }

    BowlingStyle(final String bowlingStyle) {
        this.bowlingStyle = bowlingStyle;
    }

    public static List<String> getBowlingStyles() {
        return Arrays.stream(values()).map(BowlingStyle::getBowlingStyle).collect(Collectors.toList());
    }
}
