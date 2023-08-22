package org.scsmksn.npl.auction.logic.services.impl;

import org.scsmksn.npl.auction.logic.repositories.TeamRepository;
import org.scsmksn.npl.auction.logic.services.TeamService;
import org.scsmksn.npl.auction.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.scsmksn.npl.auction.common.utils.ClientErrorHelper.createNotFound;

@Component
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Team get(final Long teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> createNotFound("Team with teamId('" + teamId + "') not found!"));
    }

    @Override
    public List<Team> list() {
        return teamRepository.findAll();
    }

    @Override
    public List<Team> listByPlayerId(Long playerId) {
        return teamRepository.findAllByPlayerId(playerId);
    }

    @Override
    public Team save(Team team) {
        final Team teamFromDB = teamRepository.saveAndFlush(team);
        return teamRepository.findById(teamFromDB.getId()).orElse(teamFromDB);
    }

    @Override
    public Team delete(Long teamId) {
        final Team team = get(teamId);
        teamRepository.deleteById(teamId);
        return team;
    }

    @Override
    public void deleteAll() {
        teamRepository.deleteAll();
    }

    @Override
    public void updateImageId(Long userId, Long imageId) {
        teamRepository.updateImageId(userId, imageId);
    }
}
