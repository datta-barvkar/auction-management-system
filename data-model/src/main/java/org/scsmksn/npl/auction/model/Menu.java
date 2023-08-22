package org.scsmksn.npl.auction.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
@Table(name = "menus")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Menu extends Entity {

    @Id
    @GenericGenerator(
        name="menuIdGenerator",
        strategy = "org.scsmksn.npl.auction.idgenerators.EntityIdGenerator",
        parameters = @Parameter(name = "table_name", value = "menus")
    )
    @GeneratedValue(generator = "menuIdGenerator")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "menu_value")
    private String value;

    @Column(name = "uri_template")
    private String uriTemplate;

    @Column(name = "selected")
    private boolean selected = false;

    @Column(name = "visible")
    private boolean visible = true;

    @Column(name = "direction")
    private String direction = "left";

    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinTable(
        name = "menus_roles",
        joinColumns = @JoinColumn(name = "menu_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();

    @Transient
    private List<Menu> subMenus = new ArrayList<>();

    @Column(name = "parent_id")
    private Long parentMenuId;

    @Transient
    @JsonIgnore
    @Override
    public String getObjectName() {
        return "menu";
    }
}
