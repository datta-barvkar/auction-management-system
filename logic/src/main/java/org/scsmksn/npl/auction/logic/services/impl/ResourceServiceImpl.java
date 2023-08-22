package org.scsmksn.npl.auction.logic.services.impl;

import org.scsmksn.npl.auction.logic.repositories.ResourceRepository;
import org.scsmksn.npl.auction.logic.services.ResourceService;
import org.scsmksn.npl.auction.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.scsmksn.npl.auction.common.utils.ClientErrorHelper.createNotFound;

@Component
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    ResourceRepository resourceRepository;

    @Override
    public Resource get(final Long resourceId) {
        return resourceRepository.findById(resourceId)
                .orElseThrow(() -> createNotFound("Resource with id('" + resourceId + "') not found!"));
    }

    @Override
    public Resource get(final String name) {
        return resourceRepository.findByName(name).orElse(null);
    }

    @Override
    public Resource save(final Resource resource) {
        final Resource savedResource = resourceRepository.saveAndFlush(resource);
        return resourceRepository.findById(savedResource.getId()).orElse(savedResource);
    }

    @Override
    public void delete(final Long id) {
        resourceRepository.delete(get(id));
    }

    @Override
    public void delete(String resourceName) {
        resourceRepository.delete(get(resourceName));
    }
}
