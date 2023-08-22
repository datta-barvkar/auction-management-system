package org.scsmksn.npl.auction.logic.repositories;

import org.scsmksn.npl.auction.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {

    @Query(
            value = "SELECT * FROM RESOURCES WHERE NAME = :name",
            nativeQuery = true
    )
    Optional<Resource> findByName(@Param("name") String name);
}
