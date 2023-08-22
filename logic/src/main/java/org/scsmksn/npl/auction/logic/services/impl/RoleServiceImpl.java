package org.scsmksn.npl.auction.logic.services.impl;

import org.scsmksn.npl.auction.logic.repositories.RoleRepository;
import org.scsmksn.npl.auction.logic.services.RoleService;
import org.scsmksn.npl.auction.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.scsmksn.npl.auction.common.utils.ClientErrorHelper.createNotFound;

@Component
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> list(final Long roleId) {
        if (roleId == null) {
            return roleRepository.findAll();
        } else {
            return roleRepository.findAllById(Collections.singletonList(roleId));
        }
    }

    @Override
    public Role save(final Role role) {
        final Role fromDB = roleRepository.saveAndFlush(role);
        return roleRepository.findById(fromDB.getId()).orElse(fromDB);
    }

    @Override
    public List<Role> saveAll(List<Role> roles) {
        return roles.stream().map(role -> alreadyExists(role) ? role : save(role)).collect(Collectors.toList());
    }

    private boolean alreadyExists(final Role role) {
        final List<Role> roles = list(null);
        return !roles.isEmpty() && roles.stream().anyMatch(fromDB -> fromDB.equals(role));
    }

    @Override
    public Role delete(final Long roleId) {
        final Role role = get(roleId);
        roleRepository.delete(role);
        return role;
    }

    private Role get(final Long roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> createNotFound("Role with id('" + roleId + "') not found!"));
    }
}
