package org.scsmksn.npl.auction.logic.services.impl;

import org.scsmksn.npl.auction.common.utils.ClientErrorHelper;
import org.scsmksn.npl.auction.logic.repositories.AccessControlRepository;
import org.scsmksn.npl.auction.logic.services.AccessControlListService;
import org.scsmksn.npl.auction.model.AccessControl;
import org.scsmksn.npl.auction.model.Params;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccessControlListServiceImpl implements AccessControlListService {

    @Autowired
    private AccessControlRepository aclRepository;

    @Override
    public AccessControl findById(final Long id) {
        return aclRepository.findById(id)
                .orElseThrow(() -> ClientErrorHelper.createNotFound("AccessControlList with id('" + id + "') not found!!"));
    }

    @Override
    public AccessControl findByName(final String name) {
        return aclRepository.findByName(name)
                .orElseThrow(() -> ClientErrorHelper.createNotFound("AccessControlList with name('" + name + "') not found!!"));
    }

    @Override
    public AccessControl findByOperationAndPath(final Params params) {
        final String operation = params.getJpaQueryParamString("operation");
        final String pathPattern = params.getJpaQueryParamString("pathPattern");
        return aclRepository.findByOperationAndPathPattern(operation, pathPattern)
                .orElseThrow(() -> ClientErrorHelper.createNotFound("AccessControlList with operation('" + operation + "') and pathPattern('" + pathPattern + "') not found!!"));
    }

    @Override
    public List<AccessControl> findAll() {
        return aclRepository.findAll();
    }

    @Override
    public List<AccessControl> findAllByGroup(final String group) {
        return aclRepository.findAllByGroup(group);
    }

    @Override
    public AccessControl save(final AccessControl accessControl) {
        final AccessControl fromDB = aclRepository.saveAndFlush(accessControl);
        return aclRepository.findById(fromDB.getId()).orElse(fromDB);
    }

    @Override
    public List<AccessControl> saveAll(final List<AccessControl> accessControls) {
        return accessControls.stream().map(this::save).collect(Collectors.toList());
    }
}
