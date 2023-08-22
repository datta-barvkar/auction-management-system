package org.scsmksn.npl.auction.web.controllers;

import org.scsmksn.npl.auction.common.controllers.GenericWebController;
import org.scsmksn.npl.auction.enums.RoleEnum;
import org.scsmksn.npl.auction.model.User;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.scsmksn.npl.auction.common.utils.AuctionConstants.USER;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.SAVE_USER;

@Controller
@RequestMapping(value = "/register", produces = MediaType.TEXT_HTML_VALUE)
public class RegistrationController extends GenericWebController {

    @GetMapping("/player")
    public String userRegistration(final Model model) {
        loadModelWithAttributes(model);
        return "user-update";
    }

    @PostMapping("/player")
    public String saveUser(@ModelAttribute(USER) final User user) {
        if (!getAuthManager().isAdmin()) {
            user.getRoles().clear();
            user.getRoles().add(RoleEnum.PLAYER.getRole());
        }
        getTemplate().sendBody(SAVE_USER, user);
        return "redirect:/login?registered=true";
    }
}
