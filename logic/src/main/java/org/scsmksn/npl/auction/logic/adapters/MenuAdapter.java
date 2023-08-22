package org.scsmksn.npl.auction.logic.adapters;

import org.scsmksn.npl.auction.logic.services.MenuService;
import org.scsmksn.npl.auction.model.Menu;
import org.scsmksn.npl.auction.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MenuAdapter {

    @Autowired
    private MenuService menuService;

    public List<Menu> listByRoles(final List<Role> roles) {
        return menuService.findAllByRoleId(roles.stream().map(Role::getId).collect(Collectors.toList()));
    }

    public List<Menu> saveAll(final List<Menu> menus) {
        return menuService.saveAll(menus);
    }
}
