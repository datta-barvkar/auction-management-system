package org.scsmksn.npl.auction.logic.repositories;

import org.scsmksn.npl.auction.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query(
            value = "SELECT * FROM teams t, teams_players tp WHERE t.id = tp.team_id and tp.player_id = :playerId",
            nativeQuery = true
    )
    List<Team> findAllByPlayerId(@Param("playerId") Long playerId);

    @Query(
            value = "UPDATE TEAMS SET TEAM_IMAGE_ID = :imageId WHERE ID = :userId",
            nativeQuery = true
    )
    void updateImageId(@Param("userId") Long userId, @Param("imageId") Long imageId);
}
