package org.scsmksn.npl.auction.logic.services;

import org.scsmksn.npl.auction.model.Credentials;
import org.scsmksn.npl.auction.model.User;

import java.util.List;

public interface UserService {

    User find(Long userId);
    List<User> findAll();
    User save(User user);
    User findByCricketProfileId(Long cricketProfileId);
    User findByUserImageId(Long userImageId);
    User delete(Long userId);
    void deleteAll();
    User validateLogin(Credentials credentials);
    Boolean usernameExists(String username);
    void updateProfileId(Long playerId, Long profileId);
    List<User> findByRoleId(Long roleId);
    void updateImageId(Long userId, Long imageId);
    List<User> saveAll(List<User> users);
    Boolean userExists(Long userId);
    Boolean userHasRole(Long userId, Long roleId);
}
