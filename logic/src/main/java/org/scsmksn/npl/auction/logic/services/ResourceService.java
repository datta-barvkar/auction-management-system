package org.scsmksn.npl.auction.logic.services;

import org.scsmksn.npl.auction.model.Resource;

public interface ResourceService {

    Resource get(Long id);
    Resource get(String name);
    Resource save(Resource resource);
    void delete(Long id);
    void delete(String resourceName);
}
