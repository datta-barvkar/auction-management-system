package org.scsmksn.npl.auction.logic.repositories;

import org.scsmksn.npl.auction.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.scsmksn.npl.auction.common.utils.AuctionConstants.IMAGE_ID;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.PLAYER_ID;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.PROFILE_ID;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.ROLE_ID;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.USERNAME;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.USER_ID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(
        value = "SELECT * FROM USERS WHERE CRICKET_PROFILE_ID = :profileId",
        nativeQuery = true
    )
    Optional<User> findByProfileId(@Param(PROFILE_ID) Long profileId);

    @Query(
        value = "SELECT * FROM USERS WHERE USER_IMAGE_ID = :userImageId",
        nativeQuery = true
    )
    Optional<User> findByUserImageId(@Param("userImageId") Long userImageId);

    @Query(
        value = "UPDATE USERS SET CRICKET_PROFILE_ID = :profileId WHERE ID = :playerId",
        nativeQuery = true
    )
    void updateProfileId(@Param(PLAYER_ID) Long playerId, @Param(PROFILE_ID) Long profileId);

    @Query(
        value = "SELECT U.* FROM USERS U, CREDENTIALS C WHERE U.CREDENTIAL_ID = C.ID AND C.USERNAME = :username",
        nativeQuery = true
    )
    Optional<User> findByUsername(@Param(USERNAME) String username);

    @Query(
        value = "UPDATE USERS SET USER_IMAGE_ID = :imageId WHERE ID = :userId",
        nativeQuery = true
    )
    void updateImageId(@Param(USER_ID) Long userId, @Param(IMAGE_ID) Long imageId);

    @Query(
        value = "SELECT U.* FROM USERS U, USERS_ROLES UR WHERE U.ID = UR.USER_ID AND UR.ROLE_ID = :roleId",
        nativeQuery = true
    )
    List<User> findByRoleId(@Param(ROLE_ID) Long roleId);

    @Query(
        value = "SELECT U.* FROM USERS U, USERS_ROLES UR WHERE U.ID = UR.USER_ID AND U.ID = :userId AND UR.ROLE_ID = :roleId",
        nativeQuery = true
    )
    Optional<User> findByIdAndRoleId(@Param(USER_ID) Long userId, @Param(ROLE_ID) Long roleId);
}
