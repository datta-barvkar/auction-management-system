package org.scsmksn.npl.auction.logic.repositories;

import org.scsmksn.npl.auction.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CricketProfileRepository extends JpaRepository<Profile, Long> {
}
