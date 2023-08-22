package org.scsmksn.npl.auction.logic.adapters;

import org.scsmksn.npl.auction.logic.services.RoleService;
import org.scsmksn.npl.auction.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleAdapter {

    @Autowired
    private RoleService roleService;

    public List<Role> list(final Long roleId) {
        return roleService.list(roleId);
    }

    public Role save(final Role role) {
        return roleService.save(role);
    }

    public Role delete(final Long roleId) {
        return roleService.delete(roleId);
    }

    public List<Role> saveAll(List<Role> roles) {
        return roleService.saveAll(roles);
    }
}
