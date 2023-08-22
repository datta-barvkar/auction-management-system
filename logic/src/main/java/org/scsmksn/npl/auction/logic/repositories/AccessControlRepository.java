package org.scsmksn.npl.auction.logic.repositories;

import org.scsmksn.npl.auction.model.AccessControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccessControlRepository extends JpaRepository<AccessControl, Long> {

    @Query(
        name = "SELECT * FROM ACCESS_CONTROL_LISTS WHERE NAME = :name",
        nativeQuery = true
    )
    Optional<AccessControl> findByName(@Param("name") final String name);

    @Query(
            name = "SELECT * FROM ACCESS_CONTROL_LISTS WHERE OPERATION = :operation AND PATH_PATTERN = :pathPattern",
            nativeQuery = true
    )
    Optional<AccessControl> findByOperationAndPathPattern(@Param("operation") final String operation
            , @Param("pathPattern") final String pathPattern);

    @Query(
            name = "SELECT * FROM ACCESS_CONTROL_LISTS WHERE ACL_GROUP = :group",
            nativeQuery = true
    )
    List<AccessControl> findAllByGroup(@Param("group") final String group);
}
