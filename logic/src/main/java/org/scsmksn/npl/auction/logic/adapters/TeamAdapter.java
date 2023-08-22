package org.scsmksn.npl.auction.logic.adapters;

import org.scsmksn.npl.auction.logic.services.ApprovalService;
import org.scsmksn.npl.auction.logic.services.TeamService;
import org.scsmksn.npl.auction.logic.services.UserService;
import org.scsmksn.npl.auction.model.Approval;
import org.scsmksn.npl.auction.model.Team;
import org.scsmksn.npl.auction.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeamAdapter {

    @Autowired
    private TeamService teamService;

    @Autowired
    private ApprovalService approvalService;

    @Autowired
    private UserService userService;

    public Team get(final Long teamId) {
        return teamService.get(teamId);
    }

    public List<Team> listTeamsForPlayer(final Long playerId) {
        return teamService.listByPlayerId(playerId);
    }

    public List<Team> list() {
        return teamService.list();
    }

    public Team save(final Team team) {
        final Team teamFromDB;
        final List<User> players = team.getPlayers();
        final Long teamId = team.getId();
        if (teamId == null) {
            team.setPlayers(new ArrayList<>());
            teamFromDB = teamService.save(team);
        } else {
            final Team teamToUpdate = teamService.get(teamId);
            final List<User> newOwners = getNewUsers(team.getOwners(), teamToUpdate.getOwners());
            final List<User> newAdmins = getNewUsers(team.getAdmins(), teamToUpdate.getAdmins());
            teamToUpdate.setOwners(newOwners);
            teamToUpdate.setAdmins(newAdmins);
            teamFromDB = teamService.save(teamToUpdate);
        }
        final List<User> playersFromDB = teamFromDB.getPlayers();
        final List<User> newPlayers = playersFromDB.isEmpty() ? players : players.stream()
                .filter(player -> playersFromDB.stream()
                        .anyMatch(playerFromDB -> !player.equals(playerFromDB))).collect(Collectors.toList());
        saveTeamVerifications(teamFromDB, newPlayers.stream().map(player -> userService.find(player.getId()))
                .collect(Collectors.toList()));
        return teamService.get(teamFromDB.getId());
    }

    private List<User> getNewUsers(final List<User> newUsers, final List<User> oldUsers) {
        if (oldUsers.isEmpty()) {
            return newUsers;
        }
        final List<User> users = oldUsers.stream()
                .filter(oldOwner -> newUsers.stream().anyMatch(newOwner -> newOwner.equals(oldOwner)))
                .collect(Collectors.toList());
        users.addAll(newUsers.stream()
                .filter(newOwner -> users.stream().anyMatch(oldOwner -> !(oldOwner.equals(newOwner))))
                .collect(Collectors.toList()));
        return users;
    }

    private void saveTeamVerifications(final Team team, final List<User> players) {
        final List<Approval> approvals = players.stream().map(user -> {
            final Approval approval = new Approval();
            approval.setTeam(team);
            approval.setPlayer(user);
            approval.setApproved(false);
            return approval;
        }).collect(Collectors.toList());
        approvalService.saveAll(approvals);
    }

    public Team delete(final Long teamId) {
        return teamService.delete(teamId);
    }

    public void deleteAll() {
        teamService.deleteAll();
    }

    public void updateImageId(final Long userId, final Long imageId) {
        teamService.updateImageId(userId, imageId);
    }
}
