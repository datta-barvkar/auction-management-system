package org.scsmksn.npl.auction.rest.controllers;

import org.scsmksn.npl.auction.common.controllers.GenericRestController;
import org.scsmksn.npl.auction.common.utils.StaticDataUtils;
import org.scsmksn.npl.auction.model.AccessControl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/accessControlManagement/accessControl")
public class AccessControlRestController extends GenericRestController {

    @PostMapping()
    public List<AccessControl> loadAuthorities() {
        final List<AccessControl> authorities = StaticDataUtils.getAuthorities();
        return authorities;
    }
}
