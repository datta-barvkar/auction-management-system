package org.scsmksn.npl.auction.logic.repositories;

import org.scsmksn.npl.auction.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Query(
        name = "SELECT * FROM MENUS M, MENUS_ROLES MR WHERE M.ID = MR.PARENT_ID AND MR.ROLE_ID IN (:roleIds)",
        nativeQuery = true
    )
    List<Menu> findByRolesIn(@Param("roleIds") List<Long> roleIds);

    @Query(
        name = "SELECT * FROM MENUS WHERE PARENT_ID IS NULL",
        nativeQuery = true
    )
    List<Menu> findByParentMenuIdIsNull();

    @Query(
        name = "SELECT * FROM MENUS WHERE PARENT_ID = :menuId",
        nativeQuery = true
    )
    List<Menu> findByParentMenuId(@Param("menuId") Long menuId);

    @Query(
        name = "SELECT * FROM MENUS M, MENUS_ROLES MR WHERE M.ID = MR.MENU_ID AND M.PARENT_ID = :menuId AND MR.ROLE_ID IN (:roleIds)",
        nativeQuery = true
    )
    List<Menu> findByParentMenuIdAndRolesIn(@Param("menuId") Long menuId, @Param("roleIds") List<Long> roleIds);

    @Query(
        name = "SELECT * FROM MENUS WHERE NAME = :name",
        nativeQuery = true
    )
    Optional<Menu> findByName(@Param("name") String name);
}
