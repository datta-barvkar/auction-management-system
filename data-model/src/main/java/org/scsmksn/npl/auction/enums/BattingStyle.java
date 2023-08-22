package org.scsmksn.npl.auction.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum BattingStyle {

    RHB("Right Handed Bat"), LHB("Left Handed Bat");

    private final String battingStyle;

    BattingStyle(final String battingStyle) {
        this.battingStyle = battingStyle;
    }

    public String getBattingStyle() {
        return battingStyle;
    }

    public static List<String> getBattingStyles() {
        final BattingStyle[] battingStyles = BattingStyle.values();
        return Arrays.stream(battingStyles).map(BattingStyle::getBattingStyle).collect(Collectors.toList());
    }
}
