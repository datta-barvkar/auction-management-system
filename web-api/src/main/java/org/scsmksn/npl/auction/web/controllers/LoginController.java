package org.scsmksn.npl.auction.web.controllers;

import org.scsmksn.npl.auction.common.controllers.GenericWebController;
import org.scsmksn.npl.auction.common.utils.HttpUtils;
import org.scsmksn.npl.auction.model.Credentials;
import org.scsmksn.npl.auction.model.User;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;

import static org.scsmksn.npl.auction.common.utils.AuctionConstants.CREDENTIALS;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.ERROR;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.VALIDATE_USER;

@Controller
@RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
public class LoginController extends GenericWebController {

    @GetMapping(value = "/login")
    public String login(final Model model) {
        model.addAttribute(CREDENTIALS, new Credentials());
        return "login";
    }

    @PostMapping(value = "/login")
    public String login(@ModelAttribute("credentials") final Credentials credentials, final Model model
            , final BindingResult result) {
        if (result.hasErrors()) {
            credentials.setPassword("");
            model.addAttribute(CREDENTIALS, credentials);
            return "login";
        }
        try {
            HttpUtils.setLoggedInUser(getTemplate().requestWithBody(VALIDATE_USER, credentials, User.class));
            final String requestPath = HttpUtils.getRedirectPath();
            HttpUtils.removeRedirectPath();
            return requestPath == null ? "redirect:/home" : ("redirect:" + requestPath);
        } catch (HttpClientErrorException ex) {
            model.addAttribute(ERROR, ex.getMessage());
            credentials.setPassword("");
            model.addAttribute(CREDENTIALS, credentials);
            return "login";
        }
    }

    @GetMapping(value = "/logout")
    public String logout() {
        HttpUtils.removeLoggedInUser();
        return "home";
    }
}
