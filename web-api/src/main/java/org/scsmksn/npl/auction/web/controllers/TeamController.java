package org.scsmksn.npl.auction.web.controllers;

import org.scsmksn.npl.auction.common.controllers.GenericWebController;
import org.scsmksn.npl.auction.model.Team;
import org.scsmksn.npl.auction.model.User;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

import static org.scsmksn.npl.auction.common.utils.AuctionConstants.OWNERS;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.PLAYERS;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.TEAM;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.TEAMS;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.TEAM_ADMINS;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.TEAM_ID;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.DELETE_TEAM;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.GET_TEAM;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.LIST_PLAYERS;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.LIST_TEAMS;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.LIST_TEAM_ADMINS;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.LIST_TEAM_OWNERS;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.SAVE_TEAM;

@Controller
@RequestMapping(value = "/team-management/team", produces = MediaType.TEXT_HTML_VALUE)
public class TeamController extends GenericWebController {

    @PostMapping(params = "create")
    public String createTeam(final Model model) {
        model.addAttribute(TEAM, new Team());
        final List<User> players = getTemplate().requestList(LIST_PLAYERS);
        model.addAttribute(PLAYERS, players);
        final List<User> owners = getTemplate().requestList(LIST_TEAM_OWNERS);
        model.addAttribute(OWNERS, owners);
        final List<User> teamAdmins = getTemplate().requestList(LIST_TEAM_ADMINS);
        model.addAttribute(TEAM_ADMINS, teamAdmins);
        return "team-update";
    }

    @PostMapping
    public String saveTeam(@ModelAttribute("team") final Team team) {
        final Team fromDB = getTemplate().requestWithBody(SAVE_TEAM, team, Team.class);
        return "redirect:/team-management/team/" + fromDB.getId();
    }

    @GetMapping
    public String listTeams(final Model model) {
        final List<Team> teams = getTemplate().requestList(LIST_TEAMS);
        model.addAttribute(TEAMS, teams.stream().peek(this::setCanUpdateOrDelete).collect(Collectors.toList()));
        return "team-list";
    }

    private void setCanUpdateOrDelete(final Team team) {
        final boolean isAdmin = getAuthManager().isAdmin();
        team.setDeletable(isAdmin);
        team.setEditable(isAdmin || getAuthManager().isRealOwnerOrTeamAdmin(String.valueOf(team.getId())));
    }

    @GetMapping(path = "/{teamId}", params = "view")
    public String viewTeam(@PathVariable(TEAM_ID) final Long teamId, final Model model) {
        final Team team = getTemplate().requestWithBody(GET_TEAM, teamId, Team.class);
        setCanUpdateOrDelete(team);
        model.addAttribute(TEAM, team);
        return "team-view";
    }

    @GetMapping(path = "/{teamId}", params = "edit")
    public String editTeam(@PathVariable(TEAM_ID) final Long teamId, final Model model) {
        model.addAttribute(TEAM, getTemplate().requestWithBody(GET_TEAM, teamId, Team.class));
        final List<User> players = getTemplate().requestList(LIST_PLAYERS);
        model.addAttribute(PLAYERS, players);
        final List<User> owners = getTemplate().requestList(LIST_TEAM_OWNERS);
        model.addAttribute(OWNERS, owners);
        final List<User> teamAdmins = getTemplate().requestList(LIST_TEAM_ADMINS);
        model.addAttribute(TEAM_ADMINS, teamAdmins);
        return "team-update";
    }

    @PutMapping(path = "/{teamId}")
    public String updateTeam(@PathVariable(TEAM_ID) final Long teamId, @ModelAttribute("team") final Team team) {
        getTemplate().sendBody(SAVE_TEAM, team);
        return "redirect:/team-management/team/" + teamId;
    }

    @DeleteMapping("/{teamId}")
    public String deleteTeam(@PathVariable(TEAM_ID) final Long teamId) {
        getTemplate().sendBody(DELETE_TEAM, teamId);
        return "redirect:/team-management/team";
    }
}
