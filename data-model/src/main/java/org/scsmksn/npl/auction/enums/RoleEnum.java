package org.scsmksn.npl.auction.enums;

import org.scsmksn.npl.auction.model.Role;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum RoleEnum {
    ADMIN(1L, "Admin")
    , AUCTION_ADMIN(2L, "AuctionAdmin")
    , AUCTIONEER(3L, "Auctioneer")
    , TEAM_OWNER(4L, "TeamOwner")
    , TEAM_ADMIN(5L, "TeamAdmin")
    , STATS_ADMIN(6L, "StatsAdmin")
    , PLAYER(7L, "Player");

    private final Long id;
    private final String name;

    RoleEnum(final Long id, final String name) {

        this.id = id;
        this.name = name;
    }

    public static List<Role> getRoles() {
        return getValues().stream().map(RoleEnum::getRole).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        final Role role = new Role();
        role.setId(id);
        role.setName(name);
        return role;
    }

    public static List<RoleEnum> getValues() {
        final RoleEnum[] values = RoleEnum.values();
        return Arrays.asList(values);
    }
}
