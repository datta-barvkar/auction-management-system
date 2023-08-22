package org.scsmksn.npl.auction.rest.controllers;

import org.scsmksn.npl.auction.common.controllers.GenericRestController;
import org.scsmksn.npl.auction.common.validators.UserValidator;
import org.scsmksn.npl.auction.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.DELETE_USER;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.GET_USER;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.LIST_USERS;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.SAVE_USER;

@RestController
@RequestMapping("/api/userManagement/user")
public class UserRestController extends GenericRestController {

    @Autowired
    private UserValidator userValidator;

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        binder.addValidators(userValidator);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable final Long id) {
        return ResponseEntity.ok(getTemplate().requestWithBody(GET_USER, id, User.class));
    }

    @GetMapping
    public ResponseEntity<List<User>> listUsers() {
        return ResponseEntity.ok(getTemplate().requestList(LIST_USERS));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Validated final User user, final BindingResult result) {
        checkValidationResult(result);
        return ResponseEntity.ok(getTemplate().requestWithBody(SAVE_USER, user, User.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable final Long id, @Validated final User user, final BindingResult result) {
        user.setId(id);
        checkValidationResult(result);
        return ResponseEntity.ok(getTemplate().requestWithBody(SAVE_USER, user, User.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable final Long id) {
        return ResponseEntity.ok(getTemplate().requestWithBody(DELETE_USER, id, User.class));
    }
}
