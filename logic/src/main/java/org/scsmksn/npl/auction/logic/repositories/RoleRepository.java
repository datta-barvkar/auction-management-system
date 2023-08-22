package org.scsmksn.npl.auction.logic.repositories;

import org.scsmksn.npl.auction.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
