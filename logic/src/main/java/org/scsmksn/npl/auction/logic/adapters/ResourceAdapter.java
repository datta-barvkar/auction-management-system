package org.scsmksn.npl.auction.logic.adapters;

import org.scsmksn.npl.auction.logic.services.ResourceService;
import org.scsmksn.npl.auction.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResourceAdapter {

    @Autowired
    private ResourceService resourceService;

    public Resource get(final Long id) { return resourceService.get(id); }

    public Resource get(final String name) {
        return resourceService.get(name);
    }

    public Resource save(final Resource resource) {
        return resourceService.save(resource);
    }

    public void delete(final Long id) { resourceService.delete(id);}
    public void delete(final String resourceName) { resourceService.delete(resourceName);}
}
