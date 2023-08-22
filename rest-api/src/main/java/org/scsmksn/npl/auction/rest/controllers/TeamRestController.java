package org.scsmksn.npl.auction.rest.controllers;

import org.scsmksn.npl.auction.common.controllers.GenericRestController;
import org.scsmksn.npl.auction.model.Team;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.DELETE_TEAM;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.GET_TEAM;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.LIST_TEAMS;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.SAVE_TEAM;

@RestController
@RequestMapping("/api/teamManagement")
public class TeamRestController extends GenericRestController {

    @GetMapping("/team/{id}")
    public Team getTeam(@PathVariable final Long id) {
        return getTemplate().requestWithBody(GET_TEAM, id, Team.class);
    }

    @GetMapping("/team")
    public List<Team> listTeams() {
        return getTemplate().requestList(LIST_TEAMS);
    }

    @PostMapping("/team")
    public Team createTeam(@RequestBody final Team team) {
        return getTemplate().requestWithBody(SAVE_TEAM, team, Team.class);
    }

    @PutMapping("/team/{id}")
    public Team updateTeam(@PathVariable final Long id, @RequestBody final Team team) {
        team.setId(id);
        return getTemplate().requestWithBody(SAVE_TEAM, team, Team.class);
    }

    @DeleteMapping("/team/{id}")
    public Team deleteTeam(@PathVariable final Long id) {
        return getTemplate().requestWithBody(DELETE_TEAM, id, Team.class);
    }
}
