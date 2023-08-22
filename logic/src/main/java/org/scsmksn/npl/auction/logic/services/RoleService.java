package org.scsmksn.npl.auction.logic.services;

import org.scsmksn.npl.auction.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> list(Long roleId);
    Role save(Role role);
    List<Role> saveAll(List<Role> roles);
    Role delete(Long roleId);
}
