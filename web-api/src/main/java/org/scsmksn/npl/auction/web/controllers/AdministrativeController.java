package org.scsmksn.npl.auction.web.controllers;

import org.scsmksn.npl.auction.common.controllers.GenericWebController;
import org.scsmksn.npl.auction.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

import static org.scsmksn.npl.auction.common.utils.AuctionConstants.ADMIN_HOME;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.PAGE_TITLE;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.USERS;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.LIST_USERS;

@Controller
@RequestMapping("/admin-management")
public class AdministrativeController extends GenericWebController {

    @GetMapping(path = "/home", params = "admin-home")
    public String adminHome(final Model model) {
        model.addAttribute(PAGE_TITLE, ADMIN_HOME);
        return "home";
    }

    @GetMapping(path = "/user", params = "view-users")
    public String listUsers(final Model model) {
        final List<User> users = getTemplate().requestList(LIST_USERS);
        model.addAttribute(USERS, users.stream().peek(this::setCanUpdateAndDelete).collect(Collectors.toList()));
        return "user-list";
    }

    @GetMapping(path = "/user", params = "add-user")
    public String createUser(final Model model) {
        model.addAllAttributes(getUserModelMap(new User()));
        return "user-update";
    }
}
