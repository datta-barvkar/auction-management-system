package org.scsmksn.npl.auction.logic.services;

import org.scsmksn.npl.auction.model.Menu;

import java.util.List;

public interface MenuService {

    List<Menu> findAll();
    List<Menu> findAllByRoleId(final List<Long> roleIds);
    List<Menu> saveAll(List<Menu> menus);
}
