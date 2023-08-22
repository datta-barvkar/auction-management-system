package org.scsmksn.npl.auction.logic.services.impl;

import org.scsmksn.npl.auction.logic.repositories.MenuRepository;
import org.scsmksn.npl.auction.logic.services.MenuService;
import org.scsmksn.npl.auction.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository repository;

    @Override
    public List<Menu> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Menu> findAllByRoleId(final List<Long> roleIds) {
        final List<Menu> menus = repository.findByRolesIn(roleIds);
        menus.forEach(menu -> menu.setSubMenus(findAllByMenuIdAndRoleId(menu.getId(), roleIds)));
        return menus;
    }

    private List<Menu> findAllByMenuIdAndRoleId(final Long menuId, final List<Long> roleIds) {
        final List<Menu> subMenus = repository.findByParentMenuIdAndRolesIn(menuId, roleIds);
        subMenus.forEach(subMenu -> subMenu.setSubMenus(findAllByMenuIdAndRoleId(subMenu.getId(), roleIds)));
        return subMenus;
    }

    @Override
    public List<Menu> saveAll(final List<Menu> menus) {
        final List<Menu> subMenus = new ArrayList<>();
        menus.forEach(menu -> {
            System.out.println("Menu >>>> " + menu.toString());
            final List<Menu> subMenuList = new ArrayList<>();
            if (!menu.getSubMenus().isEmpty()) {
                subMenuList.addAll(menu.getSubMenus());
                menu.setSubMenus(null);
            }
            final Menu fromDB;
            if (repository.findByName(menu.getName()).isPresent()) {
                fromDB = repository.findByName(menu.getName()).get();
            } else {
                fromDB = repository.saveAndFlush(menu);
            }
            subMenuList.forEach(subMenu -> subMenu.setParentMenuId(fromDB.getId()));
            subMenus.addAll(subMenuList);
        });
        if (!subMenus.isEmpty()) {
            saveAll(subMenus);
        }
        return repository.findByParentMenuIdIsNull().stream()
                .peek(menu -> menu.setSubMenus(findAllSubMenus(menu.getId())))
                .collect(Collectors.toList());
    }

    private List<Menu> findAllSubMenus(final Long menuId) {
        final List<Menu> fromDB = repository.findByParentMenuId(menuId);
        fromDB.forEach(menu -> menu.setSubMenus(findAllSubMenus(menu.getId())));
        return fromDB;
    }
}
