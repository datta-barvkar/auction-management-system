package org.scsmksn.npl.auction.logic.services;

import org.scsmksn.npl.auction.model.AccessControl;
import org.scsmksn.npl.auction.model.Params;

import java.util.List;

public interface AccessControlListService {

    AccessControl findById(Long id);
    AccessControl findByName(String name);
    AccessControl findByOperationAndPath(final Params params);
    List<AccessControl> findAll();
    List<AccessControl> findAllByGroup(String group);
    AccessControl save(AccessControl accessControl);
    List<AccessControl> saveAll(List<AccessControl> accessControls);
}
