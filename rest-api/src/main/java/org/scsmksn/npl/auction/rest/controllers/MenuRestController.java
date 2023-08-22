package org.scsmksn.npl.auction.rest.controllers;

import org.scsmksn.npl.auction.common.controllers.GenericRestController;
import org.scsmksn.npl.auction.common.validators.CollectionValidator;
import org.scsmksn.npl.auction.common.validators.MenuValidator;
import org.scsmksn.npl.auction.model.Menu;
import org.scsmksn.npl.auction.model.Role;
import org.scsmksn.npl.auction.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.LIST_MENUS_BY_ROLES;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.LIST_ROLES;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.SAVE_MENUS;
import static org.scsmksn.npl.auction.common.utils.HttpUtils.getLoggedInUser;

@RestController
@RequestMapping("/api/menuManagement/menu")
public class MenuRestController extends GenericRestController {

    @Autowired
    private MenuValidator validator;

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        if (binder.getTarget() instanceof Collection) {
            binder.addValidators(new CollectionValidator(validator));
        } else {
            binder.addValidators(validator);
        }
    }

    @GetMapping()
    public List<User> listMenus() {
        final User loggedInUser = getLoggedInUser();
        if (loggedInUser != null) {
            return getTemplate().requestListWithBody(LIST_MENUS_BY_ROLES, loggedInUser.getRoles());
        } else {
            final List<Role> roles = getTemplate().requestList(LIST_ROLES);
            return getTemplate().requestListWithBody(LIST_MENUS_BY_ROLES, roles);
        }
    }

    @PostMapping()
    public List<User> saveMenus(@RequestBody @Validated List<Menu> menus) {
        return getTemplate().requestListWithBody(SAVE_MENUS, menus);
    }
}
