package org.scsmksn.npl.auction.web.controllers;

import org.scsmksn.npl.auction.common.controllers.GenericWebController;
import org.scsmksn.npl.auction.common.utils.HttpUtils;
import org.scsmksn.npl.auction.common.validators.UserValidator;
import org.scsmksn.npl.auction.model.Credentials;
import org.scsmksn.npl.auction.model.CredentialsReset;
import org.scsmksn.npl.auction.model.Resource;
import org.scsmksn.npl.auction.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

import static org.scsmksn.npl.auction.common.utils.AuctionConstants.USER;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.USERS;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.USER_ID;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.DELETE_USER;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.GET_USER;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.LIST_USERS;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.SAVE_USER;

@Controller
@RequestMapping(value = "/user-management/user", produces = MediaType.TEXT_HTML_VALUE)
public class UserController extends GenericWebController {

    @Autowired
    private UserValidator userValidator;

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        binder.addValidators(userValidator);
    }

    @GetMapping(params = "create")
    public String createUser(final Model model) {
        model.addAllAttributes(getUserModelMap(new User()));
        return "user-update";
    }

    @PostMapping()
    public String saveUser(@ModelAttribute(USER) @Validated final User user, final BindingResult result
            , final Model model) {
        if (result.hasErrors()) {
            model.addAllAttributes(getUserModelMap(user));
            return "user-update";
        }
        final User fromDB = getTemplate().requestWithBody(SAVE_USER, user, User.class);
        return "redirect:/user-management/user/" + fromDB.getId();
    }

    @GetMapping()
    public String listUsers(final Model model) {
        final List<User> users = getTemplate().requestList(LIST_USERS);
        model.addAttribute(USERS, users.stream().peek(this::setCanUpdateAndDelete).collect(Collectors.toList()));
        return "user-list";
    }

    @GetMapping(path = "/{userId}", params = "view")
    public String viewUser(@PathVariable(USER_ID) final Long userId, final Model model) {
        final User user = getTemplate().requestWithBody(GET_USER, userId, User.class);
        setCanUpdateAndDelete(user);
        final Resource userImage = user.getUserImage();
        userImage.setDeletable(user.isDeletable());
        userImage.setEditable(user.isEditable());
        if (getAuthManager().isSameUser(String.valueOf(userId)))
        user.getCredentials().setEditable(user.isEditable());
        model.addAttribute(USER, user);
        return "user-view";
    }

    @GetMapping(path = "/{userId}", params = "edit")
    public String editUser(@PathVariable(USER_ID) final Long userId, final Model model) {
        model.addAllAttributes(getUserModelMap(getTemplate().requestWithBody(GET_USER, userId, User.class)));
        return "user-update";
    }

    @PutMapping("/{userId}")
    public String updateUser(@PathVariable(USER_ID) final Long userId
            , @ModelAttribute(USER) @Validated final User user, final BindingResult result, final Model model) {
        user.setId(userId);
        if (result.hasErrors()) {
            model.addAllAttributes(getUserModelMap(user));
            return "user-update";
        }
        getTemplate().sendBody(SAVE_USER, user);
        return "redirect:/user-management/user/" + userId;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable(USER_ID) final Long userId) {
        getTemplate().sendBody(DELETE_USER, userId);
        return "redirect:/user-management/user";
    }

    @GetMapping("/credentials")
    public String changePasswordForm() {
        return "user-credentials";
    }

    @PutMapping("/credentials")
    public String changePassword(@ModelAttribute("credentialsReset") @Validated final CredentialsReset credentialsReset
            , final BindingResult result) {
        if (result.hasErrors()) {
            return "user-credentials";
        }
        final User loggedInUser = HttpUtils.getLoggedInUser();
        final Credentials credentials = loggedInUser.getCredentials();
        final List<String> oldPasswords = credentials.getOldPasswords();
        if (oldPasswords.size() > 2) {
            oldPasswords.remove(0);
        }
        oldPasswords.add(credentialsReset.getNewPassword());
        credentials.setOldPasswords(oldPasswords);
        credentials.setPassword(credentialsReset.getNewPassword());
        getTemplate().sendBody(SAVE_USER, loggedInUser);
        return "redirect:/logout";
    }
}
