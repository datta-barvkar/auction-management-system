package org.scsmksn.npl.auction.logic.adapters;

import org.scsmksn.npl.auction.logic.services.AccessControlListService;
import org.scsmksn.npl.auction.model.AccessControl;
import org.scsmksn.npl.auction.model.Params;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccessControlListAdapter {

    @Autowired
    private AccessControlListService aclService;

    public AccessControl findById(final Long id) {
        return aclService.findById(id);
    }

    public AccessControl findByName(final String name) {
        return aclService.findByName(name);
    }

    public AccessControl findByOperationAndPath(final Params params) {
        return aclService.findByOperationAndPath(params);
    }

    public List<AccessControl> findAll() {
        return aclService.findAll();
    }

    public List<AccessControl> findAllByGroup(final String group) {
        return aclService.findAllByGroup(group);
    }

    public AccessControl save(final AccessControl accessControl) {
        return aclService.save(accessControl);
    }

    public List<AccessControl> saveAll(final List<AccessControl> accessControls) {
        return aclService.saveAll(accessControls);
    }
}
