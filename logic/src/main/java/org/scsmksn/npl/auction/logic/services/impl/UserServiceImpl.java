package org.scsmksn.npl.auction.logic.services.impl;

import org.scsmksn.npl.auction.logic.repositories.UserRepository;
import org.scsmksn.npl.auction.logic.services.UserService;
import org.scsmksn.npl.auction.model.Credentials;
import org.scsmksn.npl.auction.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.scsmksn.npl.auction.common.utils.AuctionConstants.PROFILE_ID;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.USER_ID;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.USER_IMAGE_ID;
import static org.scsmksn.npl.auction.common.utils.ClientErrorHelper.createForbidden;
import static org.scsmksn.npl.auction.common.utils.ClientErrorHelper.createNotFound;

@Component
public class UserServiceImpl implements UserService {

    private static final String NOT_FOUND = "User with %s('%s') not found!";

    @Autowired
    private UserRepository userRepository;

    @Override
    public User find(final Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> createNotFound(String.format(NOT_FOUND, USER_ID, userId)));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll(Sort.by(Sort.Order.asc("firstName")));
    }

    @Override
    public User save(final User user) {
        if (user.getId() != null) {
            final User fromDB = userRepository.findById(user.getId()).orElse(user);
            user.setCredentials(fromDB.getCredentials());
            user.setUserImage(fromDB.getUserImage());
            user.setProfile(fromDB.getProfile());
            user.setTopMenus(fromDB.getTopMenus());
        }
        final User userFromDB = userRepository.saveAndFlush(user);
        return userRepository.findById(userFromDB.getId()).orElse(userFromDB);
    }

    @Override
    public User findByCricketProfileId(final Long cricketProfileId) {
        return userRepository.findByProfileId(cricketProfileId)
                .orElseThrow(() -> createNotFound(String.format(NOT_FOUND, PROFILE_ID, cricketProfileId)));
    }

    @Override
    public User findByUserImageId(final Long userImageId) {
        return userRepository.findByUserImageId(userImageId)
                .orElseThrow(() -> createNotFound(String.format(NOT_FOUND, USER_IMAGE_ID, userImageId)));
    }

    @Override
    public User delete(Long userId) {
        final User user = find(userId);
        userRepository.deleteById(userId);
        return user;
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public User validateLogin(final Credentials credentials) {
        final User user = userRepository.findByUsername(credentials.getUsername())
                .orElseThrow(() -> createForbidden("Incorrect username!!"));
        if (user.getCredentials().getPassword().equals(credentials.getPassword())) {
            return user;
        }
        throw createForbidden("Incorrect Password!!");
    }

    @Override
    public Boolean usernameExists(final String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public Boolean userExists(final Long userId) {
        return userRepository.findById(userId).isPresent();
    }

    @Override
    public Boolean userHasRole(final Long userId, final Long roleId) {
        return userRepository.findByIdAndRoleId(userId, roleId).isPresent();
    }

    @Override
    public void updateProfileId(final Long playerId, final Long profileId) {
        userRepository.updateProfileId(playerId, profileId);
    }

    @Override
    public List<User> findByRoleId(final Long roleId) {
        return userRepository.findByRoleId(roleId);
    }

    @Override
    public void updateImageId(final Long userId, final Long imageId) {
        userRepository.updateImageId(userId, imageId);
    }

    @Override
    public List<User> saveAll(final List<User> users) {
        return users.stream().map(user -> userRepository.saveAndFlush(user)).collect(Collectors.toList());
    }
}
