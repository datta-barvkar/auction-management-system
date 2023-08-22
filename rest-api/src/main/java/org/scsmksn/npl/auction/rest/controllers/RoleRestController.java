package org.scsmksn.npl.auction.rest.controllers;

import org.scsmksn.npl.auction.common.controllers.GenericRestController;
import org.scsmksn.npl.auction.model.Role;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.LIST_ROLES;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.SAVE_ROLES;

@RestController
@RequestMapping("/api/roleManagement/role")
public class RoleRestController extends GenericRestController {

    @GetMapping
    public List<Role> listRoles() {
        return getTemplate().requestList(LIST_ROLES);
    }

    @PostMapping
    public List<Role> saveRoles(@RequestBody @Validated List<Role> roles) {
        return getTemplate().requestListWithBody(SAVE_ROLES, roles);
    }
}
