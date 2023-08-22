package org.scsmksn.npl.auction.logic.adapters;

import org.scsmksn.npl.auction.enums.RoleEnum;
import org.scsmksn.npl.auction.logic.services.UserService;
import org.scsmksn.npl.auction.model.Credentials;
import org.scsmksn.npl.auction.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserAdapter {

    @Autowired
    private UserService userService;

    public User find(final Long userId) {
        return userService.find(userId);
    }

    public List<User> findAll() {
        return userService.findAll();
    }

    public List<User> findAllAdmins() {
        return userService.findByRoleId(RoleEnum.ADMIN.getId());
    }

    public List<User> findAllAuctionAdmins() {
        return userService.findByRoleId(RoleEnum.AUCTION_ADMIN.getId());
    }

    public List<User> findAllAuctioneers() {
        return userService.findByRoleId(RoleEnum.AUCTIONEER.getId());
    }

    public List<User> findAllTeamOwners() {
        return userService.findByRoleId(RoleEnum.TEAM_OWNER.getId());
    }

    public List<User> findAllTeamAdmins() {
        return userService.findByRoleId(RoleEnum.TEAM_ADMIN.getId());
    }

    public List<User> findAllStatsAdmins() {
        return userService.findByRoleId(RoleEnum.STATS_ADMIN.getId());
    }

    public List<User> findAllPlayers() {
        return userService.findByRoleId(RoleEnum.PLAYER.getId());
    }

    User findByCricketProfileId(final Long cricketProfileId) {
        return userService.findByCricketProfileId(cricketProfileId);
    }

    User findByUserImageId(final Long userImageId){
        return userService.findByUserImageId(userImageId);
    }

    public User save(final User user) {
        return userService.save(user);
    }

    public User delete(final Long userId) {
        return userService.delete(userId);
    }

    public void deleteAll() {
        userService.deleteAll();
    }

    public User validateLogin(final Credentials credentials) {
        return userService.validateLogin(credentials);
    }

    public void updateProfileId(final Long playerId, final Long profileId) {
        userService.updateProfileId(playerId, profileId);
    }

    public void updateImageId(final Long userId, final Long imageId) {
        userService.updateImageId(userId, imageId);
    }

    public Boolean usernameExists(final String username) {
        return userService.usernameExists(username);
    }

    public Boolean userExists(final Long userId) {
        return userService.userExists(userId);
    }

    public Boolean userHasRole(final Long userId, final Long roleId) {
        return userService.userHasRole(userId,roleId);
    }
}
