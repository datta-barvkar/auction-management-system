package org.scsmksn.npl.auction.logic.services;

import org.scsmksn.npl.auction.model.Team;

import java.util.List;

public interface TeamService {

    Team get(Long teamId);
    List<Team> list();
    List<Team> listByPlayerId(Long playerId);
    Team save(Team user);
    Team delete(Long teamId);
    void deleteAll();
    void updateImageId(Long userId, Long imageId);
}
